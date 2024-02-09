package br.com.souza.eventsdrivenarchitecture.service.payment;

import br.com.souza.eventsdrivenarchitecture.database.model.PaymentHistoric;
import br.com.souza.eventsdrivenarchitecture.database.repository.IPaymentsHistoricRepository;
import br.com.souza.eventsdrivenarchitecture.enums.ValidPaymentTypeEnum;
import br.com.souza.eventsdrivenarchitecture.service.sns.AwsSnsService;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final IPaymentsHistoricRepository iPaymentsHistoricRepository;
    private final AwsSnsService awsSnsService;

    public PaymentService(IPaymentsHistoricRepository iPaymentsHistoricRepository,
                          AwsSnsService awsSnsService) {
        this.iPaymentsHistoricRepository = iPaymentsHistoricRepository;
        this.awsSnsService = awsSnsService;
    }

    public void validateOrderPayment(String message){
        JSONObject queueMessage = new JSONObject(message);
        String messageJsonString = queueMessage.getString("Message");

        JSONObject messageObject = new JSONObject(messageJsonString);
        String paymentType = messageObject.getString("paymentType");

        PaymentHistoric paymentHistoricLog = PaymentHistoric.builder()
                .id(UUID.randomUUID())
                .orderId(UUID.fromString(messageObject.getString("id")))
                .paymentStatus(isValidPaymentType(paymentType) ? PaymentStatusEnum.APPROVED.name() : PaymentStatusEnum.RECUSED.name())
                .build();

        iPaymentsHistoricRepository.save(paymentHistoricLog);
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
