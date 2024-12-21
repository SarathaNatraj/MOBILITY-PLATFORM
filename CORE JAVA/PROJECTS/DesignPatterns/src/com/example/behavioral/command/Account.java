package com.example.behavioral.command;

class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public void add(double amount) {
        balance += amount;
        System.out.println("Added: " + amount + ", New Balance: " + balance);
    }

    public void deduct(double amount) {
        balance -= amount;
        System.out.println("Deducted: " + amount + ", New Balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }
}