package com.aazsoft.mvc.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(value = "com.aazsoft.mvc.dao", 
	entityManagerFactoryRef = "mvcEntityManagerFactory", 
	transactionManagerRef = "mvcTransactionManager")
@EntityScan("com.aazsoft.mvc.domain.entity")
@PropertySource("classpath:application-dao.properties")
public class MvcMysqlConfiguration {

	@Bean(name = "mvcDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.mvc")
	public DataSource mdbDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "mvcEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean mvcEntityManagerFactory(
			@Value("#{mvcDataSource}") final DataSource dataSource) {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPersistenceUnitName("jdbc/mvc");
		em.setDataSource(dataSource);
		em.setPackagesToScan(new String[] { "com.aazsoft.mvc.domain.entity" });

		final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean(name = "mvcTransactionManager")
	public PlatformTransactionManager mvcTransactionManager(
			@Value("#{mvcEntityManagerFactory}") final EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		transactionManager
				.setTransactionSynchronization(JpaTransactionManager.SYNCHRONIZATION_NEVER);
		return transactionManager;
	}

	protected Properties additionalProperties() {
		final Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.ddl-auto", "update");
		properties.setProperty("hibernate.naming-strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		return properties;
	}
}
