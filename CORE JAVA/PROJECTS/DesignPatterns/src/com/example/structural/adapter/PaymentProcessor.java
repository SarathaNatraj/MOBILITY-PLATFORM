package com.example.structural.adapter;

public class PaymentProcessor {
    public static void main(String[] args) {
        PaymentGateway payPalAdapter = new PayPalAdapter();
        payPalAdapter.processPayment("100");

        PaymentGateway stripeAdapter = new StripeAdapter();
        stripeAdapter.processPayment("200");
    }
}
