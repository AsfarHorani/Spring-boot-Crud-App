package com.spring.task1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.task1.dao.HospitalDao;
import com.spring.task1.entites.Hospital;
import com.spring.task1.exceptionHandler.ResourseNotFoundException;

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
			.orElseThrow(()-> new ResourseNotFoundException("Hospital",hid)) ;


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
			.orElseThrow(()-> new ResourseNotFoundException("Hospital",hid)) ;

	hd.delete(entity);

}
 
}
