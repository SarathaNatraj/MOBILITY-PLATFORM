package com.example.creational.factory;

public class FactoryMethodEx {
    public static void main(String[] args) {
        DocumentCreator wordCreator = new WordDocumentCreator();
        wordCreator.printDocument();  // Prints a Word Document
        
        DocumentCreator pdfCreator = new PDFDocumentCreator();
        pdfCreator.printDocument();   // Prints a PDF Document
    }
}