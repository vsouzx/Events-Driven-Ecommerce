package br.com.souza.eventsdrivenarchitecture.database.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "payments_historic")
public class PaymentHistoric {

    @Id
    private UUID id;
    private UUID orderId;
    private String paymentStatus;

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("orderId", orderId);
        json.put("paymentStatus", paymentStatus);

        return json.toString();
    }

}
