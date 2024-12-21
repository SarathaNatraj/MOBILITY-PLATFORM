package com.example.behavioral.observer;

import java.util.ArrayList;import java.util.List;
class CricketSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    
    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(state);
        }
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }

	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.remove(o);
		
	}
}
