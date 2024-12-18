package lambdaexpressions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConsumerFunPFT {
	

	private List<Expense> expenses = new ArrayList<>();
	
	 //empty object, adding to the list 
	public void addExpense(Consumer<Expense> expenseConsumer) {
		Expense expense = new Expense("", 0.0);
		expenseConsumer.accept(expense);
		expenses.add(expense);
	}
	
	//empty object, adding to the list 
		public void addExpenseWithSupplier(Supplier<Expense> expenseConsumer) {
			//Expense expense = new Expense("", 0.0);
			Expense expense = expenseConsumer.get();
			System.out.println(" Expense with sup : "+expense);
			expenses.add(expense);
		}
	public void displayExpenses() {
		if(expenses.isEmpty()) {
			System.out.println(" No expenses ...");
			return;
		}
		System.out.println(" Expenses :");
		expenses.forEach(System.out::println);
	}
	
	public void applyTransformation(Function<Expense, Double> transformation) {
		for(Expense e: expenses) {
			double transformedAmount = transformation.apply(e); //e.getAmount = e.getAmount * 0.10, 0.85
			System.out.println(" Transformed amount for : "+e.getCatagory() + " : "+transformedAmount);
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConsumerFunPFT consumerTracker = new ConsumerFunPFT();
		
		consumerTracker.addExpense(expense -> {
			//set the property
			expense.setCatagory("Food");
			expense.setAmount(75.20);
		});

		consumerTracker.addExpense(expense -> {
			expense.setCatagory("Rent");
			expense.setAmount(15.20);
		});

		consumerTracker.displayExpenses();
		
		System.out.println(" Apply 10% discount to expenses");
		consumerTracker.applyTransformation(expense -> expense.getAmount() * 0.10); //applying discount
		
		System.out.println("Converting to USD (1 USD = 0.85 EUR");
		consumerTracker.applyTransformation(expense -> expense.getAmount() * 0.85); //currency conversion
		
		Function<String, Integer> stringLength = (str) -> str.length();
		System.out.println(stringLength.apply("Java")); // 4
		
		System.out.println(stringLength.apply("asasdasdsab  fdgdfdf"));
		
		
		consumerTracker.addExpenseWithSupplier(() -> new Expense("Misc", 520.20));
		
		Supplier<Double> randomNumber = () -> Math.random();
		System.out.println(randomNumber.get()); // Random number
	}

}
