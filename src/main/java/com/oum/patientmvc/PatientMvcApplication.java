package com.oum.patientmvc;

import com.oum.patientmvc.entities.Patient;
import com.oum.patientmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository)
    {
        return args ->{
            patientRepository.save(new Patient(null, "Oumaima", new Date(),false,20));
            patientRepository.save(new Patient(null, "Ouma", new Date(),true,22));
            patientRepository.save(new Patient(null, "Oum", new Date(),false,23));
            patientRepository.findAll().forEach(p->
            {
                System.out.println(p.getNom());
            });
        };
    }
}

