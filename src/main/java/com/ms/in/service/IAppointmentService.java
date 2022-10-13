package com.ms.in.service;

import java.util.List;

import com.ms.in.entity.Appointment;

public interface IAppointmentService {
	
	public Long saveAppointment(Appointment appointment);
	
	public List<Appointment> getAllAppointment();
	
	public Appointment getOneAppointment(Long id);
	
	public void removeAppointment(Long id);
	
	public void updateAppointment(Appointment appointment);
	
	public List<Object[]> getAppointmentByDoctor(Long docId);


}
