package com.spring.task1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.task1.dao.HospitalDao;
import com.spring.task1.entites.Hospital;

@Service
public class HospitalServiceImp implements HospitalService{

	@Autowired
	private HospitalDao hd;
	
 public HospitalServiceImp(){
	

 }

@Override
public List<Hospital> getHospitals() {
	// TODO Auto-generated method stub
	return hd.findAll();
}

@Override
public Hospital getHospital(long did) {
	// TODO Auto-generated method stub
	System.out.println(did);
	return hd.findById(did).get();

}

@Override
public Hospital addHospital(Hospital hospital) {
	// TODO Auto-generated method stub
	System.out.println(hospital);
	 hd.save(hospital);
	 return hospital;
}

@Override
public Hospital updateHospital(Hospital hospital) {
	// TODO Auto-generated method stub
	
	 hd.save(hospital);
	 return hospital;
}

@Override
public void deleteHospital(long did) {
	// TODO Auto-generated method stub

	Hospital entity =hd.findById(did).get();
	hd.delete(entity);

}
 
}
