package genericcollectionsandtypeinference;

//Java Bean class, model class - model
public class Transaction<T> {
	
	//T - generic type, String, Integer , UUID ="sdasdas1728090664"
	private T id; // ensures type safety
	private String description;
	private double amount;
	
	public Transaction(T id, String desc, double amount) {
		this.id =  id;
		this.description = desc;
		this.amount = amount;
	}
	
	

	public T getId() {
		return id;
	}



	public void setId(T id) {
		this.id = id;
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
		return "Transaction [id=" + id + ", description=" + description + ", amount=" + amount + "]";
	}
	
	

}

