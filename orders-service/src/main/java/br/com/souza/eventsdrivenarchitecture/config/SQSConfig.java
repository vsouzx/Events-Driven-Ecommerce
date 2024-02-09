package br.com.souza.eventsdrivenarchitecture.config;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class SQSConfig {

    @Value("${aws.sqs.endpoint}")
    private String amazonSqsEndpoint;

    @Bean
    public SqsAsyncClient amazonSQSAsync() {
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(amazonSqsEndpoint))
                .region(Region.US_EAST_1)
                .build();
    }

}
