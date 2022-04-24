package com.ms.in.specialization;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ms.in.entity.Specialization;
import com.ms.in.repo.SpecializationRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
//@TestMethodOrder(OrderAnnotation.class)
public class SpecializationRepositoryTest {
	
	@Autowired
	SpecializationRepository repo;
	
	/***
	 * 1. Test save operation
	 */
	@Test
	@Order(1)
	public void specTestCreate() {
		Specialization spec= new Specialization(null,"CRLDS","Cardiologists",
												 "They’re experts on the heart and blood vessels.");
		spec=repo.save(spec);
		assertNotNull(spec.getSpecId(), "Spec id not created");
		
	}
	
	/***
	 * 2. Test Display all operation
	 */
	public void specTestFetchAll() {
		
	}

}
