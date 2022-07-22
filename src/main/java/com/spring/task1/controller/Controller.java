package com.spring.task1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.task1.dao.DoctorDao;
import com.spring.task1.dao.HospitalDao;
import com.spring.task1.entites.Doctor;
import com.spring.task1.entites.Hospital;
import com.spring.task1.services.DoctorService;
import com.spring.task1.services.HospitalService;

@RestController
@RequestMapping("/api")
public class Controller {

		@Autowired
	private HospitalService hospitalService;
	@Autowired
	private DoctorService doctorService;

	@Autowired
	 private HospitalDao hr;
	@Autowired
	private DoctorDao dr;
	//Hospital routes 
    
	// index
	public String home() {
		this.doctorService.getDoctors();
		return "Welcome";
	}

	// get all hospitals
	@GetMapping("/getHospitals")
	public List<Hospital> getHospitals() {
		System.out.println("From /gethospitals");
		return this.hospitalService.getHospitals();
	}

	// get specific hospital
	@GetMapping("/getHospital/{hospitalId}")
	public Hospital getHospital(@PathVariable String hospitalId) {
		return this.hospitalService.getHospital(Long.parseLong(hospitalId));
	}

	// add a hospital
	@PostMapping("/addHospital")
	public Hospital addHospital(@RequestBody Hospital hospital) {
		return this.hospitalService.addHospital(hospital);
	}

	// update hospital
	@PutMapping("/updateHospital")
	public ResponseEntity<HttpStatus> updateHospital(@RequestBody Hospital hospital) {
		System.out.println(hospital);

		try {
			this.hospitalService.updateHospital(hospital);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("/deleteHospital/{hospitalid}")
	public ResponseEntity<HttpStatus> deleteHospital(@PathVariable String hospitalid) {
		try {
			this.hospitalService.deleteHospital(Long.parseLong(hospitalid));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	
	//Doctor
	

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

	// add a hospital
	@PostMapping("/addDoctor/{hospitalId}")
	public Doctor addDoctor(@RequestBody Doctor doctor, @PathVariable String hospitalId) {
		Hospital h =  hr.findById(Long.parseLong(hospitalId)).get();
		System.out.println(h);
		
		doctor.setHospital(h);
		
		return this.doctorService.addDoctor(doctor);
	}

	// update hospital
	@PutMapping("/updateDoctor/{doctorId}")
	public ResponseEntity<HttpStatus> updateHospital(@RequestBody String str, @PathVariable String doctorId) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JsonNode node = mapper.readTree(str);
			String newName = mapper.convertValue(node.get("name"), String.class);
			String newDept = mapper.convertValue(node.get("dept"), String.class);
            String hospitalId = mapper.convertValue(node.get("hospitalId"), String.class);
			Doctor doctor = dr.findById(Long.parseLong(doctorId)).get();
            Hospital h =  hr.findById(Long.parseLong(hospitalId)).get();
		    doctor.setName(newName);
            doctor.setDept(newDept);
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
