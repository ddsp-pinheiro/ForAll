package com.ForAll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ForAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForAllApplication.class, args);
	}

}
