package com.example.creational.factory;

public class PDFDocument extends Document {
    @Override
    public void print() {
        System.out.println("Printing a PDF Document.");
    }
}