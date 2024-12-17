package boundedtypeswildcards;

public class Transaction {
	private String description;
	private double amount;
	public Transaction(String desc, double amount) {
		// TODO Auto-generated constructor stub
		this.description=desc;
		this.amount=amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Transaction [description=" + description + ", amount=" + amount + "]";
	}
	
	
	

}
