package com.ms.in.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.in.entity.Appointment;
import com.ms.in.exception.AppointmentNotFoundException;
import com.ms.in.repo.AppointmentRepository;
import com.ms.in.service.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
	
	@Autowired
	private AppointmentRepository repo;

	@Override
	public Long saveAppointment(Appointment appointment) {
		return repo.save(appointment).getId();
	}

	@Override
	public List<Appointment> getAllAppointment() {
		/*** List<Appointment> list = repo.findAll();
		return list;
		**/
		return repo.findAll();
	}

	@Override
	public Appointment getOneAppointment(Long id) {
		Optional<Appointment> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else{
			throw new AppointmentNotFoundException("Appointment id "+ id +"not found.");
		}
	}

	@Override
	public void removeAppointment(Long id) {
		repo.deleteById(id);
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		repo.save(appointment);
	}
	

}
