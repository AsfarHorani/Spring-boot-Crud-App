package com.spring.task1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.task1.entites.Hospital;



public interface HospitalDao  extends JpaRepository<Hospital,Long>{

}
