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

import DTO.DeleteDTO;
import ObjHolders.AddPatientOH;
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
	
	@PostMapping("/addPatient")
	public Patient addPatient(@RequestBody AddPatientOH reqBody) {
		
		Patient patient = new Patient();
		Doctor d = dr.findById(reqBody.getDoctorId()).get();
		
		patient.setAddress(reqBody.getAddress());
		patient.setAge(reqBody.getAge());
		patient.setEmail(reqBody.getEmail());
		patient.setMobileNo(reqBody.getMobileNo());
		patient.setName(reqBody.getName());
		patient.setDoctor(d);
        return this.patientService.addPatient(patient);
	}
	
	@PutMapping("/updatePatient")
	public ResponseEntity updatePatient(@RequestBody UpdatePatientOH poh) {
		try {

			long doctorId = poh.getDoctorId();
			long patientId = poh.getPatientId();
			Patient patient = pr.findById(patientId).get();
			Doctor d = dr.findById(doctorId).get();
			patient.setName(poh.getName());
			patient.setAddress(poh.getAddress());
			patient.setAge(poh.getAge());
			patient.setEmail(poh.getEmail());
			patient.setMobileNo(poh.getMobileNo());
			patient.setDoctor(d);
			patient.setDeleted(poh.isDeleted());
			this.patientService.updatePatient(patient);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());

		}
	}
	
	
	
	@DeleteMapping("/deletePatient/{patientId}")
	public ResponseEntity deletePatient(@PathVariable String patientId) {
		try {
		    this.patientService.deletePatient(Long.parseLong(patientId));
			return  ResponseEntity.created(null).body(new DeleteDTO("Sucess! Patient is deleted", patientId));
		} catch (Exception e) {
			System.out.println(e);
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());

		}
	}
	
	
	
}
