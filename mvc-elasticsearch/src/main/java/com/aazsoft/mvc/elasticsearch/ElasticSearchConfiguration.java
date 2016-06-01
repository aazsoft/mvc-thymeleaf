package com.aazsoft.mvc.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.aazsoft.mvc.elasticsearch.repos.UserESRepository;

@Configuration
@EnableAutoConfiguration
public class ElasticSearchConfiguration {

	@Autowired
	UserESRepository userESRepo;

}
