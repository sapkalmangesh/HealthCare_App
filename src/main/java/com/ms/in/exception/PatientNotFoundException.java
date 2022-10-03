package com.ms.in.exception;

import org.hibernate.engine.transaction.jta.platform.internal.SunOneJtaPlatform;

public class PatientNotFoundException extends RuntimeException {
	public PatientNotFoundException() {
		super();
	}
	
	public PatientNotFoundException(String message) {
		super(message);
		
	}

}
