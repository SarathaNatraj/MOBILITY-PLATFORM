package com.example.creational.builder;

public interface ComputerBuilder {
    void buildCPU();
    void buildRAM();
    void buildStorage();
    void buildGraphicsCard();
    void buildBluetooth();
    Computer getComputer();
}
