package com.oum.patientmvc.sec.repositories;

import com.oum.patientmvc.sec.entities.appRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<appRole,Long> {
    appRole findByRoleName(String roleName);
}
