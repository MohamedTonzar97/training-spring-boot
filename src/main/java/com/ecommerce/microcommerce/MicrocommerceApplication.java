package com.ecommerce.microcommerce;

import com.ecommerce.microcommerce.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2


public class MicrocommerceApplication  {

	public static void main(String[] args) {
		SpringApplication.run(MicrocommerceApplication.class, args);
	}

	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming main method arguments
	 * @throws Exception on error

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUsername("ADMIN");
		user.setPassword("ADMIN");

		//userRepository.save(user);
	}*/
}
