package com.ms.in.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.in.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
