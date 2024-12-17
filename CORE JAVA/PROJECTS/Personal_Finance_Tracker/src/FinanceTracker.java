import java.util.ArrayList;
import java.util.List;


//Service class tracking transactions - service package 
public class FinanceTracker<T> {
	
	//generic trans list, 
	private List<Transaction<T>> transactions = new ArrayList<>();
	private double balance;
	
	//Adding Transactions
	public void addTransaction(Transaction<T> trans) {
		transactions.add(trans);
		balance += trans.getAmount();
	}
	
	public double getBalance() {
		return balance;
	}

	

	//Remove Transactions
	public void removeTransactions(Transaction<T> trans) {
		transactions.removeIf( t -> t.getId().equals(trans.getId()));
		balance -= trans.getAmount();
		System.out.println(transactions);
	}
	
	//View all transactions
	public List<Transaction<T>> getAllTransactions() {
		return transactions;
	}
	
	

}
