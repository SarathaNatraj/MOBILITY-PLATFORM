package com.example.behavioral.strategy;

public class StrategyPatternEx {
    public static void main(String[] args) {
        // Pay using PayPal
        PaymentContext context = new PaymentContext(new PayPalPayment("user@example.com", "password123"));
        context.executePayment(150.75);

        // Pay using Credit Card
        context = new PaymentContext(new CreditCardPayment("1234-5678-9012-3456", "John Doe"));
        context.executePayment(200.50);

        // Pay using Google Pay
        context = new PaymentContext(new GooglePayPayment("9876543210"));
        context.executePayment(100.00);
    }
}
