package com.spring.task1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.task1.entites.Doctor;
import com.spring.task1.entites.Patient;

public interface DoctorDao extends JpaRepository<Doctor,Long>{
	Doctor findByemail(String email);
	Doctor findBymobileNo(long mobileNo);
}
