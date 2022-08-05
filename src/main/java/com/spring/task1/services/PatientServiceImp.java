package com.spring.task1.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.task1.dao.PatientDao;
import com.spring.task1.entites.Patient;
import com.spring.task1.exceptionHandler.ResourseNotFoundException;

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
		return pd.findById(pid).orElseThrow(() -> new ResourseNotFoundException("Patient", pid));
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
		Patient entity = pd.findById(pid).orElseThrow(() -> new ResourseNotFoundException("Patient", pid));
		pd.delete(entity);
	}

}
