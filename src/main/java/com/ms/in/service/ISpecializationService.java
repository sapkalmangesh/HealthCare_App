package com.ms.in.service;

import java.util.List;
import com.ms.in.entity.Specialization;

public interface ISpecializationService {
	
	public long saveSpecialization(Specialization spec);
	
	public List<Specialization> getAllSpecialization();
	
	public void removeSpecialization(Long id);
	
	public Specialization getOneSpecialization(Long id);
	
	public void updateSpecialization(Specialization spec);

}
