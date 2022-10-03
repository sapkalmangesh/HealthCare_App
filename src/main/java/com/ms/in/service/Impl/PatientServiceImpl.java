package com.ms.in.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.in.entity.Patient;
import com.ms.in.entity.User;
import com.ms.in.exception.PatientNotFoundException;
import com.ms.in.repo.PatientRepository;
import com.ms.in.service.IPatientService;
import com.ms.in.service.IUserService;
import com.ms.in.service.constant.UserRoles;
import com.ms.in.util.UserUtil;

@Service
public class PatientServiceImpl implements IPatientService {
	
	@Autowired
	private PatientRepository repo;
	
	@Autowired
	private IUserService userservice;
	
	@Autowired
	private UserUtil userUtil;

	@Override
	public long savePatient(Patient patient) {
		Long id =  repo.save(patient).getId();
		if(id!=null) {
			User user = new User();
			user.setDisplayName(patient.getFirstName()+" "+patient.getLastName());
			user.setUsername(patient.getEMail());
			user.setPassword(userUtil.genPwd());
			user.setRole(UserRoles.PATIENT.name());
			userservice.saveUser(user);
			
			//TODO-Email part is pending
			
		}
		return id;
	}

	@Override
	public List<Patient> getAllPatients() {
		/**
		 * List<Patient> list = repo.findAll();
		   return list;
		**/
		return repo.findAll();
	}

	@Override
	public Patient getOnePatient(Long id) {
		Optional<Patient> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else{
			throw new PatientNotFoundException("patient "+ id +"not found");
		}
		 
	}

	@Override
	public void removePatient(Long id) {
		repo.deleteById(id);
	}

	@Override
	public void updatePatient(Patient patient) {
		repo.save(patient);
	}

}
