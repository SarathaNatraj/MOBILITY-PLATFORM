package lambdaexpressions;

public class Expense {
	
	private String catagory;
	private double amount;
	
	
	public Expense(String catagory, double amount) {
		super();
		this.catagory = catagory;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Expense [catagory=" + catagory + ", amount=" + amount + "]";
	}
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
