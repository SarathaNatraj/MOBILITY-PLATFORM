package genericcollectionsandtypeinference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinanceTracker<T> {
	
	//generic trans list, 
		private List<Transaction<T>> transactions = new ArrayList<>();
		private Map<T, Transaction<T>> transMap = new HashMap();
		private double balance;
		
		
	//add trans
		//Adding Transactions
		public void addTransaction(Transaction<T> trans) {
			transactions.add(trans);
			transMap.put(trans.getId(), trans);
			balance += trans.getAmount();
		}
		
		public double getBalance() {
			return balance;
		}

		

		//Remove Transactions
		public void removeTransactions(Transaction<T> trans) {
			transMap.remove(trans.getId());
			transactions.removeIf( t -> t.getId().equals(trans.getId()));
			balance -= trans.getAmount();
			System.out.println(transactions);
		}
		
		//View all transactions
		public List<Transaction<T>> getAllTransactions() {
			return transactions;
		}
		
		
		//Find transaction by ID
		public Transaction<T> findTransaction(T id){
			return transMap.get(id);
		}

		
		
		

}
