package com.financetracker;

public class BalanceUpdater extends Thread {
	private double balance;

	public double getBalance() {
		return balance;
	}

	public BalanceUpdater(double balance) {
		super();
		System.out.println(balance);
		this.balance = balance;
	}

	public synchronized void updateBalance(double amount) {
		this.balance += amount;
		System.out.println(" Updated Balance  - Balance Updater : " + balance);
	}

	@Override
	public  void  run() {

		try {
			for (int i = 0; i < 5; i++) {
				updateBalance(100); // simulate deposit
				Thread.sleep(1000); // simulate the delay
			}
		} catch (InterruptedException e) {
			System.out.println(" Balance Update Interrupted...");
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
