package com.oum.patientmvc;

import com.oum.patientmvc.entities.Patient;
import com.oum.patientmvc.repositories.PatientRepository;
import com.oum.patientmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication

public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            // Création de nouveaux patients
            patientRepository.save(new Patient(null,"oumaima",new Date(),false,20));
            patientRepository.save(new Patient(null,"ouma",new Date(),true,28));
            patientRepository.save(new Patient(null,"oum",new Date(),false,30));
            patientRepository.save(new Patient(null,"selma",new Date(),true,35));
            // Affichage de patients
            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("oumaima12","1234","1234");
            securityService.saveNewUser("ouma12","1234","1234");
            securityService.saveNewUser("kawtar","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("oumaima","USER");
            securityService.addRoleToUser("ouma","ADMIN");
            securityService.addRoleToUser("oum","USER");
            securityService.addRoleToUser("selma","USER");
        };
    }
}