package com.financetracker.concurrentcollections;

import java.util.concurrent.CompletableFuture;

public class CompleteableFutureEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double balance = 100.00;
		
		CompletableFuture.supplyAsync(() -> {
		    System.out.println("Task running on: " + Thread.currentThread().getName());
		    return "Hello";
		}).thenApply(result -> result + " World!")
		  .thenAccept(System.out::println)
		  .exceptionally(ex -> {
		      System.err.println("Error: " + ex.getMessage());
		      return null;
		  });
		
		
		
		//update the balance - future 1
		CompletableFuture<Double> balanceUpdate = CompletableFuture.supplyAsync(()->{
			System.out.println("Updating Balance ");
			double updatedBalance = balance+500;
			System.out.println("Updating Balance : "+updatedBalance);
			return updatedBalance;
			
		});

		//Track the balance - future 2
		
		CompletableFuture<Double> balanceTracker = balanceUpdate.thenApplyAsync(balanceN->{
			
			System.out.println("Tracking Balance ");
			double expense = 50;
			double reminingBalance = balanceN- expense;
			System.out.println("Remaining Balance : "+reminingBalance);
			return reminingBalance;
			});
		
		try {
			balanceTracker.get();
			
			balanceTracker.thenApply(balanceR -> balanceR)
				.thenAccept(System.out::println)
				;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
