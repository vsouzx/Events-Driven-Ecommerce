package br.com.souza.eventsdrivenarchitecture.service.sqs;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class OrdersQueueConsumer {

    @SqsListener("orders-queue")
    public void listen(String message){
        System.out.println(message);
    }
}
