package com.ms.in.service;

import java.util.List;

import com.ms.in.entity.Patient;

public interface IPatientService {
	
	public long savePatient(Patient patient);
	
	public List<Patient> getAllPatients();
	
	public Patient getOnePatient(Long id);
	
	public void removePatient(Long id);
	
	public void updatePatient(Patient patient);
	

}
