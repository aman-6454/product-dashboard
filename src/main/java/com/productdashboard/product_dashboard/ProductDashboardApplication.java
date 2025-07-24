package com.productdashboard.product_dashboard;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class ProductDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDashboardApplication.class, args);
	}


	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
