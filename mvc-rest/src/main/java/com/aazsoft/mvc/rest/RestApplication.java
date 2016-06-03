package com.aazsoft.mvc.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.aazsoft.mvc.elasticsearch.ElasticSearchConfiguration;

@SpringBootApplication
@Configuration
@ComponentScan(value = "com.aazsoft.mvc")
@EnableAutoConfiguration
@Import(value = { RestSecurityConfiguration.class, ElasticSearchConfiguration.class})
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

}
