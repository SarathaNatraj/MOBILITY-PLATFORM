package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lambdaexpressions.Expense;

public class FinanceTracker {
	
	List<Expense> expenses = new ArrayList<>();
	
	public void addExpense(Expense e) {
		expenses.add(e);
	}
	
	//Calculate Total Expenses
	public double calculateTotalExpenses() {
		return expenses.stream()
				.mapToDouble(Expense::getAmount)
				.sum();
	}

	//Filter expenses by catagory
	public List<Expense> filterExpenseByCatagory(String catagory){
		return expenses.stream()
				.filter(expense -> expense.getCatagory().equalsIgnoreCase(catagory))
				.collect(Collectors.toList());
	}
	
	//display the filter expenses by catagory
	public void displayFilteredExpenses(String catagory) {
		List<Expense> filteredExpenses = filterExpenseByCatagory(catagory);
		if(filteredExpenses.isEmpty()) {
			System.out.println(" No expenses is available for catagory "+catagory);
			return;
		}
		System.out.println("Expenses for catagory :"+catagory);
		filteredExpenses.forEach(System.out::println);
	}
	
	//display all expenses
	public void displayAllExpenses() {
	//	List<Expense> filteredExpenses = filterExpenseByCatagory(catagory);
		if(expenses.isEmpty()) {
			System.out.println(" No expenses is available.... ");
			return;
		}
		System.out.println(" All Expenses  :");
		expenses.forEach(System.out::println);
	}
	//filter expense by amount
	public void filterExpensesGreaterThan(double amount) {
		System.out.println(" Expenses greater than : "+amount);
		expenses.stream()
				.filter(expense -> expense.getAmount() > amount)
				.forEach(System.out::println);
	}
	//unique catagory 
	public void displayUniqueCatagories() {
		System.out.println(" Unique catagories :");
		expenses.stream()
				.map(Expense::getCatagory) //Groceries,Rent,Entertainment, Transportation,Groceries, Entertainment,Transportation
				.distinct() //Groceries, Rent, Entertainment, Transportation
				.forEach(System.out::println);
	}
}
