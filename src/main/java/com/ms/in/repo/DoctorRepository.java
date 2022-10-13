package com.ms.in.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ms.in.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	@Query("SELECT id,firstName,lastName FROM Doctor")
	public List<Object[]> getDoctorsName();
	
	@Query("SELECT doct FROM Doctor doct INNER JOIN doct.specialization as spc WHERE spc.specId =:specId")
	public List<Doctor> findDoctorBySpecialization(Long specId);
	
	
}
