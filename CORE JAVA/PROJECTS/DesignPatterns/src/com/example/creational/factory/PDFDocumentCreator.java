package com.example.creational.factory;

public class PDFDocumentCreator extends DocumentCreator {
    @Override
    public Document createDocument() {
        return new PDFDocument(); // Factory method creates a PdfDocument
    }
}
