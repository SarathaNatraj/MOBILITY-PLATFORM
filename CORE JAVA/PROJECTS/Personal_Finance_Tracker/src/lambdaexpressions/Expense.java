package lambdaexpressions;

public class Expense {
	
	private String catagory;
	private String description;
	private double amount;
	
	
	public Expense(String catagory, double amount) {
		super();
		this.catagory = catagory;
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.catagory = category;
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
	/*
	 * public Object getDescription() { // TODO Auto-generated method stub return
	 * null; }
	 */
	
	

}
