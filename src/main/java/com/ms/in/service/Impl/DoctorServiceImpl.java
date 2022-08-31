package com.ms.in.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.in.entity.Doctor;
import com.ms.in.exception.DoctorNotFoundException;
import com.ms.in.repo.DoctorRepository;
import com.ms.in.service.IDoctorService;

@Service
public class DoctorServiceImpl implements IDoctorService {
	
	@Autowired
	private DoctorRepository repo;

	@Override
	public Long saveDoctor(Doctor doc) 
	{
	/** Doctor doctor = repo.save(doc);
		return doctor.getId();**/
		return repo.save(doc).getId();
	}

	@Override
	public List<Doctor> getAllDoctors() 
	{
	/**	List<Doctor> list = repo.findAll();
		return list;   */
		
		return repo.findAll();
	}

	@Override
	public void removeDoctor(Long id)
	{
		/**repo.deleteById(id);  */
		
		repo.delete(getOneDoctor(id));
	}

	@Override
	public Doctor getOneDoctor(Long id) {
		Optional<Doctor> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new DoctorNotFoundException("Doctor '"+id+" ' Not Found.");
		}
	}

	@Override
	public void updateDoctor(Doctor doc) {
		repo.save(doc);
	}

}
