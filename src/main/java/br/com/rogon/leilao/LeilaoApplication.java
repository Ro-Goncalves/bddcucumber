package br.com.rogon.leilao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class LeilaoApplication {

	
	//-Dspring.profiles.active=test
	//--spring.profiles.active=test
	//-Dbrowser=firefox

@Profile("test")	
public static void main(String[] args) {
		SpringApplication.run(LeilaoApplication.class, args);
	}
	
}
