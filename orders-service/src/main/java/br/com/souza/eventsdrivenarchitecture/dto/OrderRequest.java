package br.com.souza.eventsdrivenarchitecture.dto;

import br.com.souza.eventsdrivenarchitecture.enums.PaymentTypeEnum;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @NotNull
    private UUID productId;
    @NotNull
    private Integer quantity;
    @NotNull
    private PaymentTypeEnum paymentType;

}
