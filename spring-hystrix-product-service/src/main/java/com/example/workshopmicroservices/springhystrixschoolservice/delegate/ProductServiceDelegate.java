package com.example.workshopmicroservices.springhystrixschoolservice.delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Service class
@Service
public class ProductServiceDelegate {
	@Autowired
	RestTemplate restTemplate=new RestTemplate();
	@HystrixCommand(fallbackMethod= "callProductFallback")
	public String  callProductService(int id) {
        String response =restTemplate.exchange("http://localhost:9090/Produits/{id}"
		  ,HttpMethod.GET
		  , null
		  ,new ParameterizedTypeReference<String>(){}
		  ,id).getBody();
          return "Working Product is :"+id+ "Product details:"+ response;
	}
	public String callProductFallback(int id)
	{
		//System.out.println("An ERROR Message"+schoolName);
		return "An ERROR Message"+id;
	}

	// implement a fallback method
	@HystrixCommand(fallbackMethod= "callAllProductFallback")
	public String  callAllProductService() {
		restTemplate =new RestTemplate();
		String response =restTemplate.exchange("http://localhost:9090/Produits"
				,HttpMethod.GET
				,null
				,new ParameterizedTypeReference<String>(){}
				).getBody();
		return  "All product" + response;
	}

	public String callAllProductFallback()
	{
		//System.out.println("An ERROR Message"+schoolName);
		return "Impossible d'afficher les produits";
	}

	// implement a fallback method
	@HystrixCommand(fallbackMethod= "callProductTriFallback")
	public String  callProductTriService() {
		restTemplate =new RestTemplate();
		String response =restTemplate.exchange("http://localhost:9090/Tri"
				,HttpMethod.GET
				,null
				,new ParameterizedTypeReference<String>(){}
		).getBody();
		return  "All product" + response;
	}

	public String callProductTriFallback()
	{
		return "Erreur de tri ";
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
