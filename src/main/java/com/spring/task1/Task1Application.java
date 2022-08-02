package com.spring.task1;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.task1.Utils.Department;

@SpringBootApplication
public class Task1Application {
    
	static Department a;
	public static void main(String[] args) {
		
			SpringApplication.run(Task1Application.class, args);
	}
	
	

}



