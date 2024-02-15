package br.com.souza.eventsdrivenarchitecture.service.payment;

import br.com.souza.eventsdrivenarchitecture.enums.ValidPaymentTypeEnum;
import br.com.souza.eventsdrivenarchitecture.service.sns.AwsSnsService;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {

    private final AwsSnsService awsSnsService;

    public PaymentService(AwsSnsService awsSnsService) {
        this.awsSnsService = awsSnsService;
    }

    public void validateOrderPayment(String message) throws Exception{
        System.out.println("Processando tipo de pagamento do pedido...");
        Thread.sleep(10000);

        JSONObject queueMessage = new JSONObject(message);
        String messageJsonString = queueMessage.getString("Message");

        JSONObject messageObject = null;
        try {
            messageObject = new JSONObject(messageJsonString);
        }catch (Exception e){
            log.error("Error: ", e);
            return;
        }
        String paymentType = messageObject.getString("paymentType");

        PaymentHistoricDTO paymentHistoricLog = PaymentHistoricDTO.builder()
                .orderId(UUID.fromString(messageObject.getString("id")))
                .paymentStatus(isValidPaymentType(paymentType) ? PaymentStatusEnum.APPROVED.name() : PaymentStatusEnum.RECUSED.name())
                .build();

        awsSnsService.publish(paymentHistoricLog.toString());
    }

    private boolean isValidPaymentType(String paymentType){
        List<String> validPaymentTypes = Arrays.stream(ValidPaymentTypeEnum.values())
                .map(ValidPaymentTypeEnum::name)
                .map(String::toUpperCase)
                .toList();

        return validPaymentTypes.contains(paymentType.toUpperCase());
    }
}
