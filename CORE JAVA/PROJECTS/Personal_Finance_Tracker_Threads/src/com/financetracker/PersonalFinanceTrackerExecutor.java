package com.financetracker;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PersonalFinanceTrackerExecutor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GenerateReport genReport = new GenerateReport();
		BalanceUpdater balanceUpdater = new BalanceUpdater(1000.00);
		ExpenseTracker expenseTracker = new ExpenseTracker(balanceUpdater);
		
		//Create a pool with 3 threads
		ExecutorService service = Executors.newFixedThreadPool(1); //thread pool 1
		service.submit(() -> {
		    System.out.println("Task executed by: " + Thread.currentThread().getName());
		});
		
		//submit -> start individual threads
		//service.submit(genReport);
		//service.submit(balanceUpdater);
		//service.submit(expenseTracker);
		//service.submit(expenseTracker);
		
		
		System.out.println();
		
		 ExecutorService executor = Executors.newCachedThreadPool(); //thread pool 2
		  executor.submit(() -> { System.out.println("Task executed by: " +
		  Thread.currentThread().getName()); });
		  
		  ExecutorService executor1 = Executors.newSingleThreadExecutor(); //thread pool 3 -> thread
		  executor1.submit(() -> { System.out.println("Task executed by: " +
		  Thread.currentThread().getName()); });
		//  executor1.submit(genReport);
		//  executor1.submit(expenseTracker);
		 
		System.out.println("Task outside by: " + Thread.currentThread().getName());
		
		ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(2);
		System.out.println(" Current Time : "+LocalTime.now());
		executor2.schedule(expenseTracker, 5, TimeUnit.SECONDS);
		System.out.println(" Current Time : "+LocalTime.now());
		
	
		
		
		//stops/shutdown the execution
		service.shutdown();

	}

}
