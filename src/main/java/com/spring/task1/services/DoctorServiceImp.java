package com.spring.task1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.spring.task1.dao.DoctorDao;
import com.spring.task1.entites.Doctor;
import com.spring.task1.entites.Patient;
import com.spring.task1.exceptionHandler.GeneralException;

@Service
public class DoctorServiceImp implements DoctorService{

	@Autowired
	private DoctorDao dd;
	
	@Override
	public List<Doctor> getDoctors() {
		// TODO Auto-generated method stub
		System.out.println(dd);
		return dd.findAll();	}

	@Override
	public Doctor getDoctor(long did) {
		// TODO Auto-generated method stub
		return dd.findById(did)
				.orElseThrow(()-> new GeneralException("Doctor with id "+did+" not found",HttpStatus.NOT_FOUND));
	}

	@Override
	public Doctor addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		Doctor newd = dd.findByemail(doctor.getEmail());
		System.out.println(newd);
		if(newd!=null) {
			throw new GeneralException("This email is already in use", HttpStatus.CONFLICT);
     	}
		newd = dd.findBymobileNo(doctor.getMobileNo());
		 if(newd!=null) {
				throw new GeneralException("This mobile number is already in use", HttpStatus.CONFLICT);
	     	}
		dd.save(doctor);
		return doctor;
	}

	@Override
	public Doctor updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		dd.save(doctor);
		return doctor;
	}

	@Override
	public void deleteDoctor(long did) {
		// TODO Auto-generated method stub
		Doctor entity =dd.findById(did)
				.orElseThrow(()-> new GeneralException("Doctor with id "+did+" not found",HttpStatus.NOT_FOUND));

		dd.delete(entity);
	}
	
}
