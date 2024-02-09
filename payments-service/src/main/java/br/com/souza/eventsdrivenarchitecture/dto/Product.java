package br.com.souza.eventsdrivenarchitecture.dto;

import java.math.BigDecimal;
import java.util.UUID;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;

public class Product {

    @Id
    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer quantity;

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
