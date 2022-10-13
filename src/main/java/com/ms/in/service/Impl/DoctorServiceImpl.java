package com.ms.in.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.in.entity.Doctor;
import com.ms.in.entity.User;
import com.ms.in.exception.DoctorNotFoundException;
import com.ms.in.repo.DoctorRepository;
import com.ms.in.service.IDoctorService;
import com.ms.in.service.IUserService;
import com.ms.in.service.constant.UserRoles;
import com.ms.in.util.MyCollectionUtil;
import com.ms.in.util.MyMailUtil;
import com.ms.in.util.UserUtil;

@Service
public class DoctorServiceImpl implements IDoctorService {
	
	@Autowired
	private DoctorRepository repo;
	
	@Autowired
	private IUserService userservice;
	
	@Autowired
	private UserUtil userUtil;
	
	@Autowired
	private MyMailUtil mailUtil;

	@Override
	public Long saveDoctor(Doctor doc) 
	{	
		Long id = repo.save(doc).getId();
		if(id!=null){
			String pwd = userUtil.genPwd();
			User user = new User();
			user.setDisplayName(doc.getFirstName()+" "+doc.getLastName());
			user.setUsername(doc.getEmail());
			user.setPassword(pwd);
			user.setRole(UserRoles.DOCTOR.name());
			Long genId = userservice.saveUser(user);
			if(genId!=null) {
				new Thread(new Runnable() {
					public void run() {
						String text = "Username is"+ doc.getEmail()+" , Password is"+pwd;
						mailUtil.send(doc.getEmail(), "DOCTOR ADDED", text);
					}
				}).start();
			}
			
		}
		return id;
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

	@Override
	public Map<Long, String> getDoctorsName() {
		List<Object[]> list = repo.getDoctorsName();
		return MyCollectionUtil.converToMapIndex(list);
	}

	@Override
	public List<Doctor> findDoctorBySpecialization(Long specId) {
		return repo.findDoctorBySpecialization(specId);
	}

}
