package com.juxue.config.servlet;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description: common_web
 * Created by yhsh on 2020/6/5 9:11
 * version 2.0
 * 方法说明
 */
//@Configuration
//public class EsConfig {
//
//	//localhost:9200 写在配置文件中就可以了
//	@Bean
//	RestHighLevelClient elasticsearchClient() {
//		ClientConfiguration configuration = ClientConfiguration.builder()
//				.connectedTo("47.92.1.148:9300")
//				//.withConnectTimeout(Duration.ofSeconds(5))
//				//.withSocketTimeout(Duration.ofSeconds(3))
//				//.useSsl()
//				//.withDefaultHeaders(defaultHeaders)
//				//.withBasicAuth(username, password)
//				// ... other options
//				.build();
//		RestHighLevelClient client = RestClients.create(configuration).rest();
//
//		return client;
//	}
//
//}

@Configuration
public class EsConfig extends ElasticsearchConfigurationSupport {

	@Bean
	public Client elasticsearchClient() throws UnknownHostException {
		Settings settings = Settings.builder().put("cluster.name", "docker-cluster").build();
		TransportClient client = new PreBuiltTransportClient(settings);
		client.addTransportAddress(new TransportAddress(InetAddress.getByName("47.92.1.148"), 9300));
		return client;
	}

	@Bean(name = {"elasticsearchOperations", "elasticsearchTemplate"})
	public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
		return new ElasticsearchTemplate(elasticsearchClient(), entityMapper());
	}

	// use the ElasticsearchEntityMapper
	@Bean
	@Override
	public EntityMapper entityMapper() {
		ElasticsearchEntityMapper entityMapper = new ElasticsearchEntityMapper(elasticsearchMappingContext(),
				new DefaultConversionService());
		entityMapper.setConversions(elasticsearchCustomConversions());
		return entityMapper;
	}




}