package com.aazsoft.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.aazsoft.mvc.dao")
@PropertySource("classpath:application-dao.properties")
public class MvcDaoConfiguration {

}
