package com.patientservice.PatientService.repository;

import com.patientservice.PatientService.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByLastnameIgnoreCase(String lastname, Pageable pageable);
}
