package com.spring.task1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spring.task1.Utils.Department;
import com.spring.task1.dao.DoctorDao;
import com.spring.task1.dao.HospitalDao;
import com.spring.task1.entites.Doctor;
import com.spring.task1.entites.Hospital;
import com.spring.task1.services.DoctorService;

import DTO.DeleteDTO;
import ObjHolders.AddDoctorOH;
import ObjHolders.UpdateDoctorOH;

@RestController
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private HospitalDao hr;
	@Autowired
	private DoctorDao dr;

	// get all Doctors
	@GetMapping("/getDoctors")
	public List<Doctor> getDoctors() {
		System.out.println("From /getDoctors");
		return this.doctorService.getDoctors();
	}

	// get specific Doctor
	@GetMapping("/getDoctor/{doctorId}")
	public Doctor getDoctor(@PathVariable String doctorId) {
		return this.doctorService.getDoctor(Long.parseLong(doctorId));
	}

	// add a doctor
	@PostMapping("/addDoctor/")
	public Doctor addDoctor(@RequestBody AddDoctorOH reqBody) {
		long hospitalId=  reqBody.getHospitalId();
		Hospital h = hr.findById(hospitalId).get();
		Doctor doctor = new Doctor();
		doctor.setName(reqBody.getName());
		doctor.setAddress(reqBody.getAddress());
		doctor.setAge(reqBody.getAge());
		doctor.setDept(doctor.getDept());
		doctor.setEmail(reqBody.getEmail());
		doctor.setHospital(h);
		doctor.setMobileNo(reqBody.getMobileNo());

		return this.doctorService.addDoctor(doctor);
	}

	// update hospital
	@PutMapping("/updateDoctor")
	public ResponseEntity<HttpStatus> updateDoctor(@RequestBody UpdateDoctorOH doh) {
		try {
			
			long doctorId = doh.getDoctorId();
			long hospitalId = doh.getHospitalId();
			Doctor doctor = dr.findById(doctorId).get();
			Hospital h = hr.findById(hospitalId).get();
			doctor.setName(doh.getName());
			doctor.setDept(Department.valueOf(Department.class, doh.getDept()));
			doctor.setAddress(doh.getAddress());
			doctor.setAge(doh.getAge());
			doctor.setEmail(doh.getEmail());
			doctor.setMobileNo(doh.getMobileNo());
			doctor.setHospital(h);
			doctor.setDeleted(doh.isDeleted());
			this.doctorService.updateDoctor(doctor);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("/deleteDoctor/{doctorid}")
	public ResponseEntity deleteDoctor(@PathVariable String doctorid) {
		try {
			this.doctorService.deleteDoctor(Long.parseLong(doctorid));
			return  ResponseEntity.created(null).body(new DeleteDTO("Sucess! Doctor is deleted", doctorid));
		} catch (Exception e) {
			System.out.println(e);
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());

		}
	}

}
