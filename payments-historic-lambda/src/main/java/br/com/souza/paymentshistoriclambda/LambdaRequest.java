package br.com.souza.paymentshistoriclambda;

public class LambdaRequest {

    private String paymentJson;

    public LambdaRequest(){

    }

    public String getPaymentJson() {
        return paymentJson;
    }

    public void setPaymentJson(String paymentJson) {
        this.paymentJson = paymentJson;
    }
}