package com.spring.task1.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.task1.dao.PatientDao;
import com.spring.task1.entites.Patient;


@Service
public class PatientServiceImp implements PatientService{

	@Autowired
	private PatientDao pd;
	@Autowired
	EntityManager enitityManager;
	@Override
	public List<Patient> getPatients( ) {
	// TODO Auto-generated method stub
    List<Patient> patients = pd.findAll();
  return patients;
  }
//	public List<Patient> getPatients( boolean isDeleted) {
//		// TODO Auto-generated method stub
//		Session session = enitityManager.unwrap(Session.class);
//        Filter filter = session.enableFilter("deletedPatientFilter");
//        filter.setParameter("isDeleted", isDeleted);
//        List<Patient> patients = pd.findAll();
//        session.disableFilter("deletedProductFilter");
//		return patients;
//	}

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
