package com.example.creational.builder;

public class BuilderEx {
    public static void main(String[] args) {
    	
        ComputerBuilder builder = new GamingComputerBuilder();
        
        //A aa = new A();
        //Inter aa = new A();
        Director director = new Director(builder);
        Computer computer = director.construct();

        computer.displaySpecifications();
    }
}