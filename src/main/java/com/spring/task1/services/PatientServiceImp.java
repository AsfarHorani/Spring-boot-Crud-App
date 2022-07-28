package com.spring.task1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.task1.dao.PatientDao;
import com.spring.task1.entites.Patient;


@Service
public class PatientServiceImp implements PatientService{

	@Autowired
	private PatientDao pd;
	
	@Override
	public List<Patient> getPatients() {
		// TODO Auto-generated method stub
		System.out.println("from get patients");
		return pd.findAll();
	}

	@Override
	public Patient getPatient(long pid) {
		// TODO Auto-generated method stub
		return pd.findById(pid).get();
	}

	@Override
	public Patient addPatient(Patient patient) {
		// TODO Auto-generated method stub
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
		Patient entity =pd.findById(pid).get();
		pd.delete(entity);
	}
	
	
}
