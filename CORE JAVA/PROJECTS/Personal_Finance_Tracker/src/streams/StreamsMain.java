package streams;

import lambdaexpressions.Expense;

public class StreamsMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FinanceTracker tracker = new FinanceTracker();
		
		tracker.addExpense(new Expense("Groceries", 120.50));
		tracker.addExpense(new Expense("Rent", 950.0));
		tracker.addExpense(new Expense("Entertainment", 200.50));
		tracker.addExpense(new Expense("Transportation", 20.50));
		tracker.addExpense(new Expense("Groceries", 100.50));
		tracker.addExpense(new Expense("Entertainment", 300.50));
		tracker.addExpense(new Expense("Transportation", 200.50));
		
		
		//display all
		tracker.displayAllExpenses();
		
		//calculate total expenses
		double total = tracker.calculateTotalExpenses();
		System.out.println("Total Expenses : "+total);
		
		tracker.displayFilteredExpenses("Groceries");
		
		tracker.displayFilteredExpenses("Entertainment");
		
		tracker.filterExpensesGreaterThan(200.00);
		tracker.displayUniqueCatagories();

	}

}
