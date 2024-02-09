package br.com.souza.eventsdrivenarchitecture.database.model;

import br.com.souza.eventsdrivenarchitecture.dto.ProductRequest;
import java.math.BigDecimal;
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
@Document(collection = "products")
public class Product {

    @Id
    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public Product(ProductRequest request){
        this.id = UUID.randomUUID();
        this.name = request.getName();
        this.price = request.getPrice();
        this.quantity = request.getQuantity();
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("quantity", quantity);
        json.put("price", price);

        return json.toString();
    }
}
