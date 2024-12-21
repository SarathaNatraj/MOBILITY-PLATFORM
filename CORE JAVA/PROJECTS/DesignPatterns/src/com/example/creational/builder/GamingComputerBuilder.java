package com.example.creational.builder;

public class GamingComputerBuilder implements ComputerBuilder {
    private Computer computer;

    public GamingComputerBuilder() {
        computer = new Computer("", "", "", false, false);
    }

    @Override
    public void buildCPU() {
        computer = new Computer("Intel i9", "32GB", "1TB SSD", true, true);
    }

    @Override
    public void buildRAM() {
        // Already set in buildCPU method
    }

    @Override
    public void buildStorage() {
        // Already set in buildCPU method
    }

    @Override
    public void buildGraphicsCard() {
        // Already set in buildCPU method
    }

    @Override
    public void buildBluetooth() {
        // Already set in buildCPU method
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
