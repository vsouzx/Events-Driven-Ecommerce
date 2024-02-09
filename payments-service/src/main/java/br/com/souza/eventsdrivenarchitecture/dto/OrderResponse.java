package br.com.souza.eventsdrivenarchitecture.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private UUID id;
    private Integer quantity;
    private BigDecimal amount;
    private LocalDateTime orderTime;
    private String paymentStatus;
    private Product product;

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("quantity", quantity);
        json.put("amount", amount);
        json.put("orderTime", orderTime);
        json.put("paymentStatus", paymentStatus);
        json.put("product", product.toString());

        return json.toString();
    }
}
