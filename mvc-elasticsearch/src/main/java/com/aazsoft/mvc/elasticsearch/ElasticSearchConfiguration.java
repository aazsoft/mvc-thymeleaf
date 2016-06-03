package com.aazsoft.mvc.elasticsearch;

import javax.annotation.Resource;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.aazsoft.mvc.elasticsearch.repos.UserESRepository;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:application-es.properties")
@EnableElasticsearchRepositories(basePackages = "com.aazsoft.mvc.elasticsearch")
public class ElasticSearchConfiguration {

	@Value("${spring.data.elasticsearch.host:}")
	private String host;

	@Value("${spring.data.elasticsearch.port:}")
	private int port;

	@Autowired
	UserESRepository userESRepo;

	@Resource
	private Environment environment;

	@Bean
	public Client client() {
		TransportClient client = new TransportClient();
		client.addTransportAddress(new InetSocketTransportAddress(host, port));
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchTemplate(client());
	}

}
