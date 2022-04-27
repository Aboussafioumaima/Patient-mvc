package com.oum.patientmvc.sec.service;

import com.oum.patientmvc.sec.entities.appRole;
import com.oum.patientmvc.sec.entities.appUser;

public interface SecurityService {
    appUser saveNewUser(String username, String password, String rePassword);
    appRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleToUser(String username, String roleName);
    appUser loadUserByUserName(String username);
}
