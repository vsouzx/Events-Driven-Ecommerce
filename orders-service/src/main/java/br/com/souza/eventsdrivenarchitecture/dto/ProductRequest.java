package br.com.souza.eventsdrivenarchitecture.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer quantity;
}
