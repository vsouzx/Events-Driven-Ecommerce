package br.com.souza.eventsdrivenarchitecture.service.sqs;

import br.com.souza.eventsdrivenarchitecture.service.order.OrderService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProcessedPaymentsQueueConsumer {

    private final OrderService orderService;

    public ProcessedPaymentsQueueConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @SqsListener("processed-orders-queue")
    public void listen(String message) throws Exception{
        System.out.println("Payment processed");
        JSONObject queueMessage = new JSONObject(message);
        String messageJsonString = queueMessage.getString("Message");

        JSONObject messageObject = null;
        try {
            messageObject = new JSONObject(messageJsonString);
        }catch (Exception e){
            log.error("Error: ", e);
            return;
        }

        orderService.updatePaymentStatus(UUID.fromString(messageObject.getString("orderId")), messageObject.getString("paymentStatus"));
    }
}
