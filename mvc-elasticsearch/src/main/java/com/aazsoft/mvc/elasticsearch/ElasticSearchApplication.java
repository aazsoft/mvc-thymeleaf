package com.aazsoft.mvc.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.aazsoft.mvc.dao.entity.Role;
import com.aazsoft.mvc.elasticsearch.docs.UserDocument;
import com.aazsoft.mvc.elasticsearch.repos.UserESRepository;

@SpringBootApplication
@Configuration
@ComponentScan(value = "com.aazsoft.mvc")
@EnableAutoConfiguration
public class ElasticSearchApplication implements CommandLineRunner {

	@Autowired
	UserESRepository userESRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchApplication.class, args);
	}

	private void indexAllUsers() {
		UserDocument u = new UserDocument(1L, "email", Role.ADMIN, 10, "truong");
		UserDocument u1 = new UserDocument(2L, "email1", Role.USER, 20, "user");
		UserDocument u2 = new UserDocument(3L, "email", Role.USER, 30, "hai");
		userESRepo.save(u);
		userESRepo.save(u1);
		userESRepo.save(u2);
	}

	@Override
	public void run(String... args) throws Exception {
		indexAllUsers();		
	}

}
