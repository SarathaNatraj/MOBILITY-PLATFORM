
public class PersonalFinanceTracker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FinanceTracker<Integer> tracker = new FinanceTracker();
		
		tracker.addTransaction(new Transaction<>(1, "Salary", 5000));
		tracker.addTransaction(new Transaction<>(2, "Rent", -1500));
		tracker.addTransaction(new Transaction<>(3, "Mobile Bill", -300));
		
		//Display all trans
		System.out.println(" Transactions : ");
		for(Transaction<Integer>  transaction : tracker.getAllTransactions()) {
			System.out.println(transaction);
		}
		
		System.out.println("Current Balance : "+tracker.getBalance());
		
		//Removing trans
		tracker.removeTransactions(new Transaction<>(2, "Rent", -1500)); //RENT 
		
		//Display all trans
		System.out.println("Upated Transactions : ");
		for(Transaction<Integer>  transaction : tracker.getAllTransactions()) {
				System.out.println(transaction);
		}
		
		System.out.println("Updated Balance : "+tracker.getBalance());
		
				
		
		

	}

}
