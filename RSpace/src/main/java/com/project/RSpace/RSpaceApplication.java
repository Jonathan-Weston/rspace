package com.project.RSpace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
@EnableEncryptableProperties
@SpringBootApplication
public class RSpaceApplication {

	public static void main(String[] args) {
		System.setProperty("jasypt.encryptor.password", "apiKey");
		SpringApplication.run(RSpaceApplication.class, args);
	}

}
