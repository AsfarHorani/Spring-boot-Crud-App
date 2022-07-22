package com.spring.task1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.task1.entites.Doctor;

public interface DoctorDao extends JpaRepository<Doctor,Long>{

}
