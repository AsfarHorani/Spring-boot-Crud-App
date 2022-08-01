package com.spring.task1.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.task1.Utils.Department;
import com.spring.task1.dao.DoctorDao;
import com.spring.task1.dao.PatientDao;
import com.spring.task1.entites.*;
import com.spring.task1.exceptionHandler.GlobalExceptionHandler;
import com.spring.task1.exceptionHandler.ResourseNotFoundException;
import com.spring.task1.services.DoctorService;
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
	public DoctorService doctorService;
	
	@GetMapping("/getPatients")
	public List<Patient> getPatients() {

		return this.patientService.getPatients();
	}

	@GetMapping("/getPatient/{patientId}")
	public ResponseEntity getPatient(@PathVariable String patientId) {
		System.out.println(patientId);

		Patient patient = this.patientService.getPatient(Long.parseLong(patientId));
		return new ResponseEntity<>(patient, HttpStatus.OK);


	}

	@PostMapping("/addPatient")
	public ResponseEntity addPatient(@RequestBody AddPatientOH reqBody) {

		Patient patient = new Patient();
		Doctor d = doctorService.getDoctor(reqBody.getDoctorId());
		patient.setAddress(reqBody.getAddress());
		patient.setAge(reqBody.getAge());
		patient.setEmail(reqBody.getEmail());
		patient.setMobileNo(reqBody.getMobileNo());
		patient.setName(reqBody.getName());
		patient.setDoctor(d);
		Patient createdPatient = this.patientService.addPatient(patient);
		return new ResponseEntity<>(createdPatient, HttpStatus.OK);
	}

	@PutMapping("/updatePatient")
	public ResponseEntity updatePatient(@RequestBody UpdatePatientOH poh) {
		
			long doctorId = poh.getDoctorId();
			long patientId = poh.getPatientId();
			Patient patient = this.patientService.getPatient(patientId);
			Doctor d = doctorService.getDoctor(doctorId);

			patient.setName(poh.getName());
			patient.setAddress(poh.getAddress());
			patient.setAge(poh.getAge());
			patient.setEmail(poh.getEmail());
			patient.setMobileNo(poh.getMobileNo());
			patient.setDoctor(d);
			patient.setDeleted(poh.isDeleted());
			Patient upatedPatient = this.patientService.updatePatient(patient);
			return new ResponseEntity<>(upatedPatient, HttpStatus.OK);

	}

	@DeleteMapping("/deletePatient/{patientId}")
	public ResponseEntity <DeleteDTO>deletePatient(@PathVariable String patientId) {
	
			this.patientService.deletePatient(Long.parseLong(patientId));
			return new ResponseEntity<DeleteDTO>(new DeleteDTO("Sucess! Patient is deleted", patientId),  HttpStatus.OK);
		
	}

}
