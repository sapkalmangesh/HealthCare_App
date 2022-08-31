package com.ms.in.service;

import java.util.List;

import com.ms.in.entity.Doctor;

public interface IDoctorService {
	
	public Long saveDoctor(Doctor doc);
	
	public List<Doctor> getAllDoctors();
	
	public void removeDoctor(Long id);
	
	public Doctor getOneDoctor(Long id);
	
	public void updateDoctor(Doctor doc);

}
