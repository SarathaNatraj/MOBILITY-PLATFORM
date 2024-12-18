package lambdaexpressions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaPersonalExpenseTracker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Expense> expenses = new ArrayList<>();
		expenses.add(new Expense("Groceries", 120.50));
		expenses.add(new Expense("Rent", 950.0));
		expenses.add(new Expense("Entertainment", 200.50));
		expenses.add(new Expense("Transportation", 20.50));
		expenses.add(new Expense("Groceries", 100.50));
		expenses.add(new Expense("Entertainment", 300.50));
		expenses.add(new Expense("Transportation", 200.50));
		
		
		//Filter expenses above 200 -> lambda expr with single line
		Predicate<Expense> highExpenseFilterCondition = expense -> expense.getAmount() > 200.00;
		//List<Expense> highExpenses = filterExpenses(expenses,highExpenseFilterCondition);
		List<Expense> highExpenses = expenses.stream()
				.filter(expense -> {
					if(highExpenseFilterCondition.test(expense)) {
						return true;
					}
				return false;
				
				}).collect(Collectors.toList());
		
		
		System.out.println("Expenses above 200");
		highExpenses.forEach(System.out::println);
		
		//Filter expense by catagory, calculate total expense for one catagory
		double totalGroceriesExpsense = expenses.stream().
										//lambda expr with multi line
										filter(expense -> {
											if(expense.getCatagory().equalsIgnoreCase("Groceries")) {
												System.out.println(" Processing expense "+expense.getCatagory());
												return true;
											}
											return false;
										}).mapToDouble(Expense::getAmount)//100.50 , 120.50
										.sum(); //100.50 + 120.50 => 221.0
		
		List amounts = expenses.stream().
				//lambda expr with multi line
				filter(expense -> {
					if(expense.getCatagory().equalsIgnoreCase("Groceries")) {
						System.out.println(" Processing expense "+expense.getCatagory());
						return true;
					}
					return false;
				}).map(Expense::getAmount)//100.50 , 120.50
				.collect(Collectors.toList());
		
		System.out.println(amounts);
		System.out.println(" Total Groceries Expense : "+totalGroceriesExpsense);
		
		

	}

	private static List<Expense> filterExpenses(List<Expense> expenses, Predicate<Expense> condition) {
		// TODO Auto-generated method stub
		
		List<Expense> filteredExpenses = new ArrayList<Expense>();
		for(Expense expense : expenses) {
			if(condition.test(expense)) {
				filteredExpenses.add(expense);
			}
		}
		return filteredExpenses;
	}

}
