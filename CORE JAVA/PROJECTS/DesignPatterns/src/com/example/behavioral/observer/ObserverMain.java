package com.example.behavioral.observer;

public class ObserverMain {
    public static void main(String[] args) {
        CricketSubject subject = new CricketSubject();

        Observer observer1 = new CricketSubjectObserver("Akash");
        Observer observer2 = new CricketSubjectObserver("Harish");

        subject.addObserver(observer1);
        subject.addObserver(observer2);

        subject.setState("2024");
      //subject.removeObserver( observer1);
        subject.setState(" 2025");
    }
}
