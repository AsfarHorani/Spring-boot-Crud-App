package com.spring.task1.exceptionHandler;
import  org.springframework.web.bind.annotation.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value={ResourseNotFoundException.class})
	public ResponseEntity<ApiResponse> handleApiRequestException(ResourseNotFoundException e) {

		ApiResponse apiresponse = new ApiResponse(e.getMessage(),false, HttpStatus.NOT_FOUND) ;
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.NOT_FOUND);
	}
	
	
	
     
	
	@ExceptionHandler(value={Exception.class})
	public ResponseEntity<ApiResponse> handleApiRequestException(Exception e) {

		ApiResponse apiresponse = new ApiResponse(e.getMessage(),false, HttpStatus.INTERNAL_SERVER_ERROR );
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
     
	
	
	
}
