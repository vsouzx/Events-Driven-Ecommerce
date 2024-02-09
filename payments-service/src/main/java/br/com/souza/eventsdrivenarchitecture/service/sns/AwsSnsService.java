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
    private final Topic processedPaymentsTopic;

    public AwsSnsService(AmazonSNS snsClient,
                         Topic processedPaymentsTopic){
        this.snsClient = snsClient;
        this.processedPaymentsTopic = processedPaymentsTopic;
    }

    public void publish(String message){
        snsClient.publish(processedPaymentsTopic.getTopicArn(), message);
        System.out.println("Message published");
    }
}