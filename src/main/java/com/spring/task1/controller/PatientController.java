package com.spring.task1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.task1.Utils.Department;
import com.spring.task1.dao.DoctorDao;
import com.spring.task1.dao.PatientDao;
import com.spring.task1.entites.*;
import com.spring.task1.services.PatientService;

import ObjHolders.UpdateDoctorOH;
import ObjHolders.UpdatePatientOH;

@RestController
public class PatientController {

	@Autowired
	public PatientService patientService; 
	@Autowired
	private DoctorDao dr;
	
	@Autowired
	private PatientDao pr;

	@GetMapping("/getPatients")
	public List<Patient> getPatients() {
		System.out.println("from get patients");

		return this.patientService.getPatients();
	}
	
	
	@GetMapping("/getPatient/{patientId}")
	public Patient getPatient(@PathVariable String patientId) {
		System.out.println(patientId);
		return this.patientService.getPatient(Long.parseLong(patientId));
	}
	
	@PostMapping("/addPatient/{doctorId}")
	public Patient addPatient(@RequestBody Patient patient, @PathVariable String doctorId) {
		Doctor d = dr.findById(Long.parseLong(doctorId)).get();

		patient.setDoctor(d);

		return this.patientService.addPatient(patient);
	}
	
	@PutMapping("/updatePatient/{patientId}")
	public ResponseEntity<HttpStatus> updatePatient(@RequestBody UpdatePatientOH poh, @PathVariable String patientId) {
		try {

			long doctorId = poh.getDoctorId();

			Patient patient = pr.findById(Long.parseLong(patientId)).get();
			Doctor d = dr.findById(doctorId).get();
			patient.setName(poh.getName());
			patient.setAddress(poh.getAddress());
			patient.setAge(poh.getAge());
			patient.setEmail(poh.getEmail());
			patient.setMobileNo(poh.getMobileNo());
			patient.setDoctor(d);
			this.patientService.updatePatient(patient);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	
	
	@DeleteMapping("/deletePatient/{patientId}")
	public ResponseEntity<HttpStatus> deletePatient(@PathVariable String patientId) {
		try {
			this.patientService.deletePatient(Long.parseLong(patientId));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	
	
}
