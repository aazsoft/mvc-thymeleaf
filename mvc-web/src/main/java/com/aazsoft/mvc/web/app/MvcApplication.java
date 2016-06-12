package com.aazsoft.mvc.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.aazsoft.mvc.security.config.SecurityConfiguration;
import com.aazsoft.mvc.web.config.MvcConfiguration;

@SpringBootApplication
@Configuration
@ComponentScan(value = "com.aazsoft.mvc")
@EnableAutoConfiguration
@Import(value = { MvcConfiguration.class, SecurityConfiguration.class })
public class MvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcApplication.class, args);
	}
}
