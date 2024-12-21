package com.example.creational.singleton;

public class Singleton {
    // Step 2: Static variable to hold the single instance
    private static Singleton instance;

    // Step 1: Private constructor
    private Singleton() { }

    // Step 3: Public method to provide access
    public static Singleton getInstance() {
        if (instance == null) {
        	System.out.println(" inside if :: ");
            instance = new Singleton();
        }
        return instance;
    }
    
    public static void main(String[] args) {
		Singleton singleObj = Singleton.getInstance();
		
		System.out.println(" Single Object : "+singleObj);
		
		Singleton singleObj2 = Singleton.getInstance();
		System.out.println(" Single Object 2: "+singleObj2);
		
		
	}
}
