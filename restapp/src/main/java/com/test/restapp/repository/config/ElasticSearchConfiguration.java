package com.test.restapp.repository.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

@EnableElasticsearchRepositories(basePackages = "com.test.restapp.repository")
@Configuration
public class ElasticSearchConfiguration {

    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private String esPort;

    @Value("${elasticsearch.cluster.name}")
    private String esClusterName;

    @Bean
    public Client client() throws Exception {
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", esClusterName)
                .build();
        System.out.printf("coming here");
        return TransportClient.builder()
                .settings(settings)
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName(esHost), Integer.parseInt(esPort))
                );
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }
}
