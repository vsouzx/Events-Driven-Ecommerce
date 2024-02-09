package br.com.souza.eventsdrivenarchitecture.service.sqs;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProcessedPaymentsQueueConsumer {

    @SqsListener("processed-orders-queue")
    public void listen(String message){
        System.out.println(message);
        JSONObject queueMessage = new JSONObject(message);
        String messageJsonString = queueMessage.getString("Message");

        JSONObject messageObject = null;
        try {
            messageObject = new JSONObject(messageJsonString);
        }catch (Exception e){
            log.error("Error: ", e);
            return;
        }
        String paymentType = messageObject.getString("paymentStatus");
        System.out.println(paymentType);
    }
}
