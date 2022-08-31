package com.ms.in.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.in.entity.Specialization;
import com.ms.in.exception.SpecializationNotFoundException;
import com.ms.in.repo.SpecializationRepository;
import com.ms.in.service.ISpecializationService;
import com.ms.in.util.MyCollectionUtil;

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
		//repo.deleteById(id);
		repo.delete(getOneSpecialization(id));
		}

	@Override
	public Specialization getOneSpecialization(Long id) {
		 Optional<Specialization> opt = repo.findById(id);	
		    if(opt.isPresent()) {
		    	return opt.get();
		    }else {
		    	throw new SpecializationNotFoundException("Specialization id '" + id + "'  Not Found.");		
			    }
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);
	}

	@Override
	public boolean isSpecCodeExist(String specCode) {
		/**	Integer count = repo.getspecCodeCount(specCode);
		boolean exist = count>0 ? true : false;
		return exist;**/
		return repo.getspecCodeCount(specCode)>0;
	}

	@Override
	public boolean isSpecCodeExistForEdit(String specCode, long specId) {
		return repo.getSpecCodeCountForEdit(specCode,specId)>0;
	}

	@Override
	public Map<Long, String> getSpecIdAndName() {
		List<Object[]> list= repo.getSpecIdAndName();
		Map<Long,String> map = MyCollectionUtil.convertToMap(list);
		return map;
	}

	@Override
	public Long getSpecializationCount() {
		// TODO Auto-generated method stub
		return null;
	}	
}
