package com.spring.task1;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.task1.Utils.Department;

@SpringBootApplication
@EnableSwagger2
public class Task1Application {
    
	static Department a;
	public static void main(String[] args) {
		
		a = Department.valueOf(Department.class,"GASTROENTEROLOGIST");
		for (Department d : Department.values()) {
			System.out.println(d);
		}		SpringApplication.run(Task1Application.class, args);
	}

}



