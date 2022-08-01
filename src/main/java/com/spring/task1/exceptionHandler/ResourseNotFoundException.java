package com.spring.task1.exceptionHandler;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ResourseNotFoundException  extends RuntimeException {
	private static final long serialVersionUID = 1L;
    String enitity;
    long id;
	
	public ResourseNotFoundException(String Enitity, long id) {
		super("Could not found "+Enitity+" with id "+id);
	    this.enitity= Enitity;
		this.id = id;
	}
    
    
	
    
	
	
	
}
