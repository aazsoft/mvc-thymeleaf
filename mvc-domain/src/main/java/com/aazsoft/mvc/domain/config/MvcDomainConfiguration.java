package com.aazsoft.mvc.domain.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.aazsoft.mvc.domain.entity")
public class MvcDomainConfiguration {

}
