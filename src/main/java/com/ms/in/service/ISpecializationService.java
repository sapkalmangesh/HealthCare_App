package com.ms.in.service;

import java.util.List;
import java.util.Map;

import com.ms.in.entity.Specialization;

public interface ISpecializationService {
	
	public long saveSpecialization(Specialization spec);
	
	public List<Specialization> getAllSpecialization();
	
	public void removeSpecialization(Long id);
	
	public Specialization getOneSpecialization(Long id);
	
	public void updateSpecialization(Specialization spec);
	
	public boolean isSpecCodeExist(String specCode);
	
	public boolean isSpecCodeExistForEdit(String specCode, long specId);
	
	//for module integration(Doctor and Specialization)
	Map<Long,String> getSpecIdAndName();
	
	public Long getSpecializationCount();

}
