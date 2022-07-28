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
	@PostMapping("/addDoctor/{hospitalId}")
	public Doctor addDoctor(@RequestBody Doctor doctor, @PathVariable String hospitalId) {
		Hospital h = hr.findById(Long.parseLong(hospitalId)).get();
		System.out.println(h);

		doctor.setHospital(h);

		return this.doctorService.addDoctor(doctor);
	}

	// update hospital
	@PutMapping("/updateDoctor/{doctorId}")
	public ResponseEntity<HttpStatus> updateDoctor(@RequestBody UpdateDoctorOH doh, @PathVariable String doctorId) {
		try {

			long hospitalId = doh.getHospitalId();

			Doctor doctor = dr.findById(Long.parseLong(doctorId)).get();
			Hospital h = hr.findById(hospitalId).get();
			doctor.setName(doh.getName());
			doctor.setDept(Department.valueOf(Department.class, doh.getDept()));
			doctor.setAddress(doh.getAddress());
			doctor.setAge(doh.getAge());
			doctor.setEmail(doh.getEmail());
			doctor.setMobileNo(doh.getMobileNo());
			doctor.setHospital(h);
			this.doctorService.updateDoctor(doctor);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("/deleteDoctor/{doctorid}")
	public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable String doctorid) {
		try {
			this.doctorService.deleteDoctor(Long.parseLong(doctorid));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
