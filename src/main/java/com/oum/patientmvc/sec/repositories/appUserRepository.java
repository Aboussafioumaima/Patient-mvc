package com.oum.patientmvc.sec.repositories;

import com.oum.patientmvc.sec.entities.appUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface appUserRepository extends JpaRepository<appUser,String> {
    appUser findByUsername(String username);
}

