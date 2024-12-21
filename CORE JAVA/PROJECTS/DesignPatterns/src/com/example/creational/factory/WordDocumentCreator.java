package com.example.creational.factory;

public class WordDocumentCreator extends DocumentCreator {
    @Override
    public Document createDocument() {
        return new WordDocument(); // Factory method creates a WordDocument
    }
}
