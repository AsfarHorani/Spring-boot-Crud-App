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
import org.springframework.web.bind.annotation.RestController;


import com.spring.task1.entites.Hospital;
import com.spring.task1.services.HospitalService;

import io.swagger.annotations.Api;
import responseDTO.DeleteDTO;

@RestController
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Hospital.")

public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

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
		System.out.println(hospital);
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
	public ResponseEntity deleteHospital(@PathVariable String hospitalid) {
		try {
			this.hospitalService.deleteHospital(Long.parseLong(hospitalid));
			return  ResponseEntity.created(null).body(new DeleteDTO("Hospital is deleted", hospitalid));
		} catch (Exception e) {
			System.out.println(e);
			
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());

		}
	}

	
	
	
}
