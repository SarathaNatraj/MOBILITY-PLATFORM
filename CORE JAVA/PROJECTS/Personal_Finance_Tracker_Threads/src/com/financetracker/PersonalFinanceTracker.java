package com.financetracker;

public class PersonalFinanceTracker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BalanceUpdater balanceUpdater = new BalanceUpdater(1000.00); //Thread object created - new state
		ExpenseTracker expenseTracker = new ExpenseTracker(balanceUpdater);
		
		GenerateReport report = new GenerateReport();
		Thread t = new Thread(report); //New State
		
		//Multithreading 
		balanceUpdater.start();
		expenseTracker.start();
		//t.setPriority(Thread.);
		t.start(); //Runnable
		

	}

}
