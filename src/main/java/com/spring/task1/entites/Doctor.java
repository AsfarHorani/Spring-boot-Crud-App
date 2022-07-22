package com.spring.task1.entites;

import javax.persistence.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Doctor {
	@Id
  private long id;
  private String name;
  private String dept;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "hid", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
  private Hospital hospital;
  

public Hospital getHospital() {
	return hospital;
}
public void setHospital(Hospital hospital) {
	this.hospital = hospital;
}
@Override
public String toString() {
	return "Doctor [id=" + id + ", name=" + name + ", dept=" + dept + ", hospital=" + hospital + "]";
}
public Doctor() {
	super();
	// TODO Auto-generated constructor stub
}
public Doctor(long id, String name, String dept) {
	super();
	this.id = id;
	this.name = name;
	this.dept = dept;
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
public String getDept() {
	return dept;
}
public void setDept(String dept) {
	this.dept = dept;
}
	
}
