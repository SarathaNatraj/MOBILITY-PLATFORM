package com.example.structural.adapter;

public class PayPalAdapter implements PaymentGateway {
    private PayPal payPal;

    public PayPalAdapter() {
        this.payPal = new PayPal();
    }

    @Override
    public void processPayment(String amount) {
        payPal.makePayment(amount);
    }
}