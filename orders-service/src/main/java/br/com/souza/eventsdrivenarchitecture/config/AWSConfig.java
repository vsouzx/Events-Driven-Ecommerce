package br.com.souza.eventsdrivenarchitecture.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Value("${aws.region}")
    private String region;
    @Value("${aws.access-key-id}")
    private String accessKeyId;
    @Value("${aws.secret-access-key}")
    private String secretKey;
    @Value("${aws.sns.orders-topic}")
    private String ordersTopicArn;

    @Bean
    public AmazonSNS amazonSNSBuilder(){
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretKey);

        return AmazonSNSClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    @Bean(name = "ordersTopic")
    public Topic snsOrdersTopicBuilder(){
        return new Topic().withTopicArn(ordersTopicArn);
    }
}
