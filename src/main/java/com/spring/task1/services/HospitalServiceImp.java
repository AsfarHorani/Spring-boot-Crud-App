package com.spring.task1.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.spring.task1.dao.HospitalDao;
import com.spring.task1.entites.Hospital;
import com.spring.task1.exceptionHandler.GeneralException;

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
public Hospital getHospital(long hid) {
	// TODO Auto-generated method stub
	System.out.println(hid);
	return hd.findById(hid)
			.orElseThrow(()-> new GeneralException("Hospital with id "+hid+" not found",HttpStatus.NOT_FOUND));


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
public void deleteHospital(long hid) {
	// TODO Auto-generated method stub

	Hospital entity =hd.findById(hid)
			.orElseThrow(()-> new GeneralException("Hospital with id "+hid+" not found",HttpStatus.NOT_FOUND));

	hd.delete(entity);

}
 
}
