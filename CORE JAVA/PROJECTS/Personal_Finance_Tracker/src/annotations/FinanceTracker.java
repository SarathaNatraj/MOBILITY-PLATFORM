package annotations;

public class FinanceTracker {
	@SuppressWarnings("unchecked")
	
	public void addTransction(Transaction t) {
		System.out.println(" Adding Trans");
	}
	
	
	@ValidateAmount(min = 0.0)
	public void validateTransaction(Transaction t) throws Exception {
		if(t.getAmount()<0) {
			throw new Exception("Invalid Exception "+t);
		}
		
	}

	
	@Deprecated
	public void printSummary() {
		System.out.println(" This method is deprecated.., call showSummary() instead");
	}
	
	public void showSummary() {
		System.out.println("Showing the summary ...");
	}
}
