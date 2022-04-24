package com.ms.in.specialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

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
	public void specTestCreate() {
		
	}
	
	/***
	 * 2. Test Display all operation
	 */
	public void specTestFetchAll() {
		
	}

}
