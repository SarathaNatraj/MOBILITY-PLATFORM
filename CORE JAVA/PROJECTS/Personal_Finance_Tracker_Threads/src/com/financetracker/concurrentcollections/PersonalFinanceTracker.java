package com.financetracker.concurrentcollections;

import java.util.concurrent.ConcurrentHashMap;

public class PersonalFinanceTracker {
	
	ConcurrentHashMap<String,Double> accountBalance = new ConcurrentHashMap<String, Double>();
	
	public void addAccount(String accountName, double balance) {
		accountBalance.put(accountName, balance);
	}
	
	public void updateBalance(String accountName, double newBalance) {
		accountBalance.computeIfPresent(accountName, (key,balance)-> balance+newBalance);
	}
	
	public void displayBalances() {
		accountBalance.forEach((key, value) -> System.out.println(key + ": " + value));
	}

}
