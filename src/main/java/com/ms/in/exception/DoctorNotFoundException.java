package com.ms.in.exception;

public class DoctorNotFoundException extends RuntimeException {
	public DoctorNotFoundException() {
		super();
	}
	public DoctorNotFoundException(String message){
		super(message);
	}

}
