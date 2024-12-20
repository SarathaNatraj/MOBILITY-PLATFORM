package com.financetracker;

import java.time.LocalTime;

public class ExpenseTracker extends Thread {
	private BalanceUpdater balanceUpdater;

	public ExpenseTracker(BalanceUpdater balanceUpdater) {
		super();
		this.balanceUpdater = balanceUpdater;
	}

	@Override
	public void run() {
		try {
			System.out.println(" Current Time : - ExpenseTracker "+LocalTime.now());
			for (int i = 0; i < 5; i++) {
				synchronized (balanceUpdater) {
					
					double currentBalance = balanceUpdater.getBalance();
					double expense = 50;
					if (currentBalance >= expense) {
						balanceUpdater.updateBalance(-expense);
						System.out.println(" Tracked Expense  - Expense Tracker : " + expense);
					} else {
						System.out.println("Insufficient Balance funds to track");
					}
					Thread.sleep(1500);

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Expense Tracker Interrupted .");
		}
	}

}
