package com.example.behavioral.observer;

class CricketSubjectObserver implements Observer {
    private String name;

    public CricketSubjectObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received update: " + message);
    }
}
