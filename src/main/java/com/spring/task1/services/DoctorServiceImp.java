package com.spring.task1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.task1.dao.DoctorDao;
import com.spring.task1.entites.Doctor;

@Service
public class DoctorServiceImp implements DoctorService{

	@Autowired
	private DoctorDao dd;
	
	@Override
	public List<Doctor> getDoctors() {
		// TODO Auto-generated method stub
		System.out.println(dd);
		return dd.findAll();
	}

	@Override
	public Doctor getDoctor(long hid) {
		// TODO Auto-generated method stub
		return dd.findById(hid).get();
	}

	@Override
	public Doctor addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		
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
	public void deleteDoctor(long hid) {
		// TODO Auto-generated method stub
		Doctor entity =dd.findById(hid).get();
		dd.delete(entity);
	}

}
