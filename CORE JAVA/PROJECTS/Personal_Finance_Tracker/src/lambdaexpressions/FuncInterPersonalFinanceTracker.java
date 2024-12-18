package lambdaexpressions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FuncInterPersonalFinanceTracker {

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
		
		
		//public boolean process(Expense exp){
		// 	if(exp.getAmount() > 100.00){
		// 		return true;
		// 	}
		// return false;
		//}
		ExpenseProcessor highExpenseProcessor = expense -> expense.getAmount() > 200.00;
		List<Expense> highExpenses = filterExpenses(expenses, highExpenseProcessor);
		System.out.println(" High Expenses : "+highExpenses);
		
		ExpenseProcessor groceriesExpenseProcessor = expense -> expense.getCatagory().equalsIgnoreCase("Groceries");
		List<Expense> groceries = filterExpenses(expenses, groceriesExpenseProcessor);
		System.out.println(" Groceries Expenses "+groceries);

	}

	private static List<Expense> filterExpenses(List<Expense> expenses, ExpenseProcessor condition) {
		// TODO Auto-generated method stub
		
		List<Expense> filteredExpenses = new ArrayList<Expense>();
		for(Expense expense : expenses) {
			if(condition.test(expense)) {
				System.out.println(expense.getCatagory());
				filteredExpenses.add(expense);
			}
		}
		return filteredExpenses;
	}
}
