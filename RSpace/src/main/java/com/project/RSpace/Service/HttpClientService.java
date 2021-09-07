package com.project.RSpace.Service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class HttpClientService {
	
	@Autowired
    ApplicationContext appContext;
		
	public Map getSamples(){
		RestTemplate restTemplate = new RestTemplate();
		
		JasyptService service = appContext.getBean(JasyptService.class);
		
	    //Add a ClientHttpRequestInterceptor to the RestTemplate
	    restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor(){
	        @Override
	        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
	            request.getHeaders().set("apiKey", service.getKey());
	            return execution.execute(request, body);
	        }
	    });

	    ResponseEntity<Map> response = restTemplate.getForEntity("https://demos.researchspace.com/api/inventory/v1/samples", Map.class);        
	    return response.getBody();
	}
}
