package boundedtypeswildcards;

import java.util.ArrayList;
import java.util.List;

public class BoundedPersonalTracker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FinanceTracker<Income> incomeTracker = new FinanceTracker<Income>();
		incomeTracker.addTransaction(new Income("Salary", 5000));
		incomeTracker.addTransaction(new Income("Bonus", 2000));
		

		FinanceTracker<Expense> expenseTracker = new FinanceTracker<Expense>();
		expenseTracker.addTransaction(new Expense("Rent", -3000));
		expenseTracker.addTransaction(new Expense("Mobile", -1000));
		
		
		System.out.println("Income Transactions");
		incomeTracker.printTransactions();
		System.out.println("Total Income :: "+incomeTracker.calculateBalance());
		
		System.out.println("Expense Transactions");
		expenseTracker.printTransactions();
		System.out.println("Total Expense :: "+expenseTracker.calculateBalance());
		
		
		List<Transaction> allTrans = new ArrayList<Transaction>();
		allTrans.add(new Income("Freelance Work", 1000));
		allTrans.add(new Expense("Utilities", 200));
		
		FinanceTracker.displayTransactions(allTrans);
		
		
	}

}
