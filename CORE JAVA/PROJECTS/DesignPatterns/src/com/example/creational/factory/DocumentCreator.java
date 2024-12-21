package com.example.creational.factory;

public abstract class DocumentCreator {
    // The factory method
    public abstract Document createDocument();

    public void printDocument() {
        Document document = createDocument();
        document.print();
    }
}
