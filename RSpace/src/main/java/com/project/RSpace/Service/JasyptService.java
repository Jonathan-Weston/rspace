package com.project.RSpace.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("encrypted.properties")
public class JasyptService {
	
    @Value("${encrypted.key}")
    private String key;

    public String getKey() {
        return key;
    }
}
