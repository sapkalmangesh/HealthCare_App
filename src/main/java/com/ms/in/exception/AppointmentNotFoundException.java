package com.ms.in.exception;

public class AppointmentNotFoundException extends RuntimeException {
	
	public AppointmentNotFoundException() {
		 super();
		}

	public AppointmentNotFoundException(String message) {
		 super(message);
		 
	}
	
}
