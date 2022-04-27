package com.oum.patientmvc.sec.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.oum.patientmvc.sec.entities.appRole;
import com.oum.patientmvc.sec.entities.appUser;
import com.oum.patientmvc.sec.repositories.AppRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private com.oum.patientmvc.sec.repositories.appUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public appUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Password does not match");
        String hashedPWD = passwordEncoder.encode(password);
        appUser appUser = new appUser();
        appUser.setUserId(UUID.randomUUID().toString()); // Génerer des identifiants en se référant à la date systeme
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        com.oum.patientmvc.sec.entities.appUser savedAppUser = appUserRepository.save(appUser);
        return appUser;
    }

    @Override
    public appRole saveNewRole(String roleName, String description) {
        appRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole != null) throw new RuntimeException("Role " +roleName+ " already exists.");
        appRole = new appRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        com.oum.patientmvc.sec.entities.appRole savedAppRole = appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        appUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) throw new RuntimeException("User not found.");
        appRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null) throw new RuntimeException("Role not found.");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleToUser(String username, String roleName) {
        appUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) throw new RuntimeException("User not found.");
        appRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null) throw new RuntimeException("Role not found.");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public appUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}
