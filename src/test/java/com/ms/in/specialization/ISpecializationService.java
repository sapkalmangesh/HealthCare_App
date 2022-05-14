package com.ms.in.specialization;

import java.util.List;

import com.ms.in.entity.Specialization;

public interface ISpecializationService {
	long saveSpecialization(Specialization spec);
	List<Specialization> getAllSpecialization();
	Specialization specgetOneSpecialization(Long id);
	void deleteSpecialization(Long id);
	void updateSpecialization(Specialization spec);
	

}
