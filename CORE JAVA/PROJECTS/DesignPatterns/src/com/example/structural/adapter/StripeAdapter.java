package com.example.structural.adapter;

public class StripeAdapter implements PaymentGateway {
    private Stripe stripe;

    public StripeAdapter() {
        this.stripe = new Stripe();
    }

    @Override
    public void processPayment(String amount) {
        stripe.initiatePayment(amount);
    }
}