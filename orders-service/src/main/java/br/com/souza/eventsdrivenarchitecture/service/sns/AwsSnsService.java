package br.com.souza.eventsdrivenarchitecture.service.sns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AwsSnsService {
    private final AmazonSNS snsClient;
    private final Topic ordersTopic;

    public AwsSnsService(AmazonSNS snsClient,
                         @Qualifier("ordersTopic") Topic ordersTopic){
        this.snsClient = snsClient;
        this.ordersTopic = ordersTopic;
    }

    public void publish(String message){
        snsClient.publish(ordersTopic.getTopicArn(), message);
        System.out.println("Message published");
    }
}