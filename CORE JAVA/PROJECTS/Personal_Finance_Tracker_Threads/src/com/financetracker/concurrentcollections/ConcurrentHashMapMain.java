package com.financetracker.concurrentcollections;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHashMapMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PersonalFinanceTracker tracker = new PersonalFinanceTracker();
		
		
		tracker.addAccount("Savings", 1000);
		tracker.addAccount("Current", 2000);
		tracker.addAccount("Investment", 3000);
		
		
		ExecutorService executer = Executors.newFixedThreadPool(3);
		
		executer.execute(()-> tracker.updateBalance("Savings", 200));
		executer.execute(()-> tracker.updateBalance("Current", -200));
		executer.execute(()-> tracker.updateBalance("Investment", 300));
		
		tracker.displayBalances();
		
		
		executer.shutdown();
	}

}
