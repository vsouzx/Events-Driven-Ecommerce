package br.com.souza.eventsdrivenarchitecture.service.sqs;

import br.com.souza.eventsdrivenarchitecture.service.payment.PaymentService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class OrdersQueueConsumer {

    private final PaymentService paymentService;

    public OrdersQueueConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @SqsListener("orders-queue")
    public void listen(String message){
        System.out.println(message);
        paymentService.validateOrderPayment(message);
    }
}
