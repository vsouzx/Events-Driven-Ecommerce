package br.com.souza.eventsdrivenarchitecture.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SNSConfig {

    @Value("${aws.region}")
    private String region;
    @Value("${aws.sns.processed-orders-topic}")
    private String processedOrdersTopicArn;

    @Bean
    public AmazonSNS amazonSNSBuilder(){
        return AmazonSNSClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(region)
                .build();
    }

    @Bean
    public Topic snsProcessedOrdersTopicBuilder(){
        return new Topic().withTopicArn(processedOrdersTopicArn);
    }
}
