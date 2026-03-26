package com.eazybytes.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer  // Lec 80 <-----------  Building Config Server using Spring Cloud Config
public class ConfigserverApplication {

	/*  		 http://localhost:8071/accounts/prod

				http://localhost:8071/accounts/default
				http://localhost:8071/accounts/qa
				http://localhost:8071/accounts/prod


				http://localhost:8071/loans/default
				http://localhost:8071/loans/qa
				http://localhost:8071/loans/prod


				http://localhost:8071/loans/default
				http://localhost:8071/cards/qa
				http://localhost:8071/cards/prod


	 */



	public static void main(String[] args) {
		SpringApplication.run(ConfigserverApplication.class, args);
	}

}
