package annotations;

public class Transaction {
	private int id;
	private String description;
	private double amount;
	public Transaction(int id,String desc, double amount) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.description=desc;
		this.amount=amount;
	}
	public String getDescription() {
		return description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "Transaction [id=" + id +  "description=" + description + ", amount=" + amount + "]";
	}
	
	
	

}
