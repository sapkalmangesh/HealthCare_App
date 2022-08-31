package com.ms.in.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ms.in.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
		
	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode")
	Integer getspecCodeCount(String specCode);
	
	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode AND specId!=:specId")
	Integer getSpecCodeCountForEdit(String specCode, long specId);
	
	@Query("SELECT specId,specName FROM Specialization ")
	List<Object[]> getSpecIdAndName();

}
