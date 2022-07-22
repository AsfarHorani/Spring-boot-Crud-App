package com.spring.task1.entites;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class Hospital {
	@Id
	private long id;
	private String name;
	
//	@OneToMany(mappedBy ="hospital")
//	private List<Doctor> doctors;
	
	public Hospital(long id, String name, List<Doctor> doctors) {
		super();
		this.id = id;
		this.name = name;
	}

	public Hospital(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Hospital() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
//	public List<Doctor> getDoctors() {
//		return doctors;
//	}
//
//	public void setDoctors(List<Doctor> doctors) {
//		this.doctors = doctors;
//	}

	@Override
	public String toString() {
		return "Hospital [id=" + id + ", name=" + name + "]";
	}

	
	
}
