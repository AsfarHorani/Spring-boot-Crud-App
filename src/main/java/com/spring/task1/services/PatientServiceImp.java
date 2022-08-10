package com.spring.task1.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.spring.task1.dao.PatientDao;
import com.spring.task1.entites.Patient;
import com.spring.task1.exceptionHandler.GeneralException;

@Service
public class PatientServiceImp implements PatientService {

	@Autowired
	private PatientDao pd;
	@Autowired
	EntityManager enitityManager;

	@Override
	public List<Patient> getPatients() {
		// TODO Auto-generated method stub
		List<Patient> patients = pd.findAll();
		return patients;
	}

	@Override
	public Patient getPatient(long pid) {
		return pd.findById(pid)
				.orElseThrow(()-> new NoSuchElementException("Patient with id "+pid+" not found"));
	}

	@Override
	public Patient addPatient(Patient patient) {
		// TODO Auto-generated method stub
		
		Patient newP = pd.findByemail(patient.getEmail());
		System.out.println(newP);
		if(newP!=null) {
			throw new GeneralException("This email is already in use", HttpStatus.CONFLICT);
     	}
		 newP = pd.findBymobileNo(patient.getMobileNo());
		 if(newP!=null) {
				throw new GeneralException("This mobile number is already in use", HttpStatus.CONFLICT);
	     	}
    	pd.save(patient);
		return patient;
	}

	@Override
	public Patient updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		pd.save(patient);
		return patient;
	}

	@Override
	public void deletePatient(long pid) {
		// TODO Auto-generated method stub
		Patient entity = pd.findById(pid)
				.orElseThrow(()-> new GeneralException("Patient with id "+pid+" not found",HttpStatus.NOT_FOUND));
		pd.delete(entity);
	}

}
