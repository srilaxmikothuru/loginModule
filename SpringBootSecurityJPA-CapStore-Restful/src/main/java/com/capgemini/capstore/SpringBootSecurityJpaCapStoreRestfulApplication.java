package com.capgemini.capstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages= {"com.capgemini.capstore"})
@EntityScan(basePackages="com.capgemini.capstore.entities")
@EnableJpaRepositories(basePackages="com.capgemini.capstore.dao")
public class SpringBootSecurityJpaCapStoreRestfulApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJpaCapStoreRestfulApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootSecurityJpaCapStoreRestfulApplication.class);
	}
}
