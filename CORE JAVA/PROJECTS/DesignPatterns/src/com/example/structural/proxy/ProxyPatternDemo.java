package com.example.structural.proxy;

public class ProxyPatternDemo {
    public static void main(String[] args) {
        Image image = new ProxyImage("test_image.jpg");
        // Image is loaded and displayed
        image.display();
        // Image is displayed without loading again
        image.display();
    }
}