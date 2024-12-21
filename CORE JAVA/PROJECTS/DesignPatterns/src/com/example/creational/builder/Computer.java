package com.example.creational.builder;

public class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    private boolean isGraphicsCard;
    private boolean isBluetooth;

    public Computer(String CPU, String RAM, String storage, boolean isGraphicsCard, boolean isBluetooth) {
        this.CPU = CPU;
        this.RAM = RAM;
        this.storage = storage;
        this.isGraphicsCard = isGraphicsCard;
        this.isBluetooth = isBluetooth;
    }

    public void displaySpecifications() {
        System.out.println("CPU: " + CPU);
        System.out.println("RAM: " + RAM);
        System.out.println("Storage: " + storage);
        System.out.println("Graphics Card: " + (isGraphicsCard ? "Yes" : "No"));
        System.out.println("Bluetooth: " + (isBluetooth ? "Yes" : "No"));
    }
}