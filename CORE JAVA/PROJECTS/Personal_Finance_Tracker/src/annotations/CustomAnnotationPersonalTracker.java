package annotations;

public class CustomAnnotationPersonalTracker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
FinanceTracker tracker = new FinanceTracker();
Transaction t1 = new Transaction(1, "Salary", 5000);
Transaction t2 = new Transaction(2, "Rent", -500);
		
		tracker.addTransction(t1);
		
		//tracker.addTransction();
		
		
		//validate the transactions
		try {
			tracker.validateTransaction(t1);
			System.out.println(" Transaction 1 is valid.");
			
			tracker.validateTransaction(t2);
			System.out.println(" Transaction 2 is valid.");
		}catch (Exception e) {
			System.err.println(e.getMessage());
			// TODO: handle exception
		}
		
		tracker.printSummary();
		
		tracker.showSummary();
	}

}
