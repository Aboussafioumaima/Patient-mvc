package com.oum.patientmvc.repositories;

import com.oum.patientmvc.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {

}
