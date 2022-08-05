package com.spring.task1.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.spring.task1.services.HospitalService;

import io.swagger.annotations.Api;
import requestDTOs.AddDoctorOH;
import requestDTOs.UpdateDoctorOH;
import responseDTO.DeleteDTO;

@RestController
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Doctor.")

public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private HospitalService hs;
	

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
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/addDoctor/")
	public ResponseEntity addDoctor(@Valid @RequestBody AddDoctorOH reqBody) {
		long hospitalId = reqBody.getHospitalId();
		Hospital h = null;
		if (hospitalId != 0l) {
			this.hs.getHospital(hospitalId);
		}
		Doctor doctor = new Doctor();
		doctor.setName(reqBody.getName());
		doctor.setAddress(reqBody.getAddress());
		doctor.setAge(reqBody.getAge());
		doctor.setDept(doctor.getDept());
		doctor.setEmail(reqBody.getEmail());
		doctor.setHospital(h);
		doctor.setMobileNo(reqBody.getMobileNo());

		Doctor newDoc = this.doctorService.addDoctor(doctor);
		return new ResponseEntity<>(newDoc, HttpStatus.OK);

	}

	// update hospital
	@PutMapping("/updateDoctor")
	public ResponseEntity updateDoctor(@Valid @RequestBody UpdateDoctorOH doh) {

			long doctorId = doh.getDoctorId();
			long hospitalId = doh.getHospitalId();
			Doctor doctor =   doctorService.getDoctor(doctorId);
			Hospital h = null;
			if (hospitalId != 0l) {
				this.hs.getHospital(hospitalId);
			}			doctor.setName(doh.getName());
			doctor.setDept(Department.valueOf(Department.class, doh.getDept()));
			doctor.setAddress(doh.getAddress());
			doctor.setAge(doh.getAge());
			doctor.setEmail(doh.getEmail());
			doctor.setMobileNo(doh.getMobileNo());
			doctor.setHospital(h);
			doctor.setDeleted(doh.isDeleted());
			Doctor updatedDoctor = this.doctorService.updateDoctor(doctor);
			return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
		
		
		
	}

	@DeleteMapping("/deleteDoctor/{doctorid}")
	public ResponseEntity deleteDoctor(@PathVariable String doctorid) {
		
			this.doctorService.deleteDoctor(Long.parseLong(doctorid));
			return new ResponseEntity<DeleteDTO>(new DeleteDTO("Doctor", doctorid),  HttpStatus.OK);
			
	
	}

}
