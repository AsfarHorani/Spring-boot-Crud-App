package com.spring.task1.exceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import  org.springframework.web.bind.annotation.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.validation.FieldError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value={ResourseNotFoundException.class})
	public ResponseEntity<ApiResponse> handleApiRequestException(ResourseNotFoundException e) {

		ApiResponse apiresponse = new ApiResponse(e.getMessage(),false, HttpStatus.NOT_FOUND) ;
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.NOT_FOUND);
	}
	

	   @ExceptionHandler({MethodArgumentNotValidException.class})
	    public ResponseEntity<Object> handleMethodArgumentNotValidException(
	            MethodArgumentNotValidException exception) {
	        var errors = exception.getBindingResult().getAllErrors()
	                .stream()
	                .collect(
	                        Collectors.toMap(error ->
	                                        ((FieldError) error).getField(),
	                                DefaultMessageSourceResolvable::getDefaultMessage));
	        
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	    }
	
	@ExceptionHandler(value={Exception.class})
	public ResponseEntity<ApiResponse> handleApiRequestException(Exception e) {

		ApiResponse apiresponse = new ApiResponse(e.getMessage(),false, HttpStatus.INTERNAL_SERVER_ERROR );
        System.out.println("exception general"+ e);

		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
     
	
	
	
}
