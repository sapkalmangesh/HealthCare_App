package com.ms.in.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ms.in.entity.Appointment;

public interface AppointmentRepository extends JpaRepository< Appointment, Long> {
	
	@Query("SELECT appt.date, appt.slots,appt.fee FROM Appointment appt INNER JOIN appt.doctor as doctor WHERE doctor.id=:docId")
	public List<Object[]> getAppointmentByDoctor(Long docId);

} 


     

