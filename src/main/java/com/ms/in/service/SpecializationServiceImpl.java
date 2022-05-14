package com.ms.in.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.in.entity.Specialization;
import com.ms.in.repo.SpecializationRepository;

@Service
public class SpecializationServiceImpl implements ISpecializationService {
	
	@Autowired
	private SpecializationRepository repo;

	@Override
	public long saveSpecialization(Specialization spec) {
		return repo.save(spec).getSpecId();
	}

	@Override
	public List<Specialization> getAllSpecialization() {
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		repo.deleteById(id);
		}

	@Override
	public Specialization getOneSpecialization(Long id) {
		 Optional<Specialization> opt = repo.findById(id);	
		    if(opt.isPresent()) {
		    	return opt.get();
		    }else {
		    	return null;		
			    }
	}

	@Override
	public void updateSpecialization(Specialization spec) {
   
	}	
}
