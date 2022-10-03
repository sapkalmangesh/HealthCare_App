package com.ms.in.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.in.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
