package br.com.souza.eventsdrivenarchitecture.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {

    private String error;
    private int status;
}
