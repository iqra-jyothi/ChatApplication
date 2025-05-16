package com.jyo.practiceprojectformList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = "com.jyo.practiceprojectformList.Repository")
//@EntityScan(basePackages = "com.jyo.practiceprojectformList.entity")

//@SpringBootApplication(scanBasePackages = "com.jyo.practiceprojectformList")
@SpringBootApplication
public class PracticeprojectformListApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeprojectformListApplication.class, args);
	}

}
