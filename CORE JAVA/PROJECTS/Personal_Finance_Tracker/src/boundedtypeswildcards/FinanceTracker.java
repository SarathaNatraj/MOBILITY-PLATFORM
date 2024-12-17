package boundedtypeswildcards;

import java.util.ArrayList;
import java.util.List;
import boundedtypeswildcards.Transaction;
//limiting / restricting types - bounded types - it will accept sub classes of Transcations
public class FinanceTracker<T extends Transaction> {
	
	private List<Transaction> transactions = new ArrayList();
	
	public void addTransaction(T transaction) {
		transactions.add(transaction);
	}
	
	@SuppressWarnings("unchecked")
	public double calculateBalance() {
		//return transactions.stream().flatMapToDouble(Transaction::getAmount).sum();
		//return 0.0;
		return transactions.stream()
                .mapToDouble(transaction -> transaction.getAmount())  // Using lambda to call getAmount
                .sum();

	}
	
	public void printTransactions() {
		transactions.forEach(System.out :: println);
	}
	
	
	//? - wildcard - either Expense or  Income 
	//T extend baseclass - accept only subclasses
	// ? extends baseclass - any/unknown type of baseclass
	public static void displayTransactions(List<? extends Transaction> trans1) {
		System.out.println(" Display Trans");
		for(Transaction trans : trans1) {
			System.out.println(trans);
		}
	}

}
