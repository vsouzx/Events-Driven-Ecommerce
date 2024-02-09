package br.com.souza.eventsdrivenarchitecture.database.model;

import br.com.souza.eventsdrivenarchitecture.dto.OrderRequest;
import br.com.souza.eventsdrivenarchitecture.enums.PaymentStatusEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "orders")
public class Order {

    @Id
    private UUID id;
    private UUID productId;
    private Integer quantity;
    private BigDecimal amount;
    private LocalDateTime orderTime;
    private String paymentStatus;

    public Order(OrderRequest request, Product product){
        this.id = UUID.randomUUID();
        this.productId = request.getProductId();
        this.quantity = request.getQuantity();
        this.amount = product.getPrice().multiply(new BigDecimal(request.getQuantity()));
        this.orderTime = LocalDateTime.now();
        this.paymentStatus = PaymentStatusEnum.PENDING.name().toUpperCase();
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("productId", productId);
        json.put("quantity", quantity);
        json.put("amount", amount);
        json.put("paymentStatus", paymentStatus);
        json.put("orderTime", orderTime);

        return json.toString();
    }
}
