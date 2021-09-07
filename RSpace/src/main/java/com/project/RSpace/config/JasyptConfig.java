package com.project.RSpace.config;

import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;

@Configuration
@EncryptablePropertySource("classpath:encrypted.properties")
public class JasyptConfig {

}
