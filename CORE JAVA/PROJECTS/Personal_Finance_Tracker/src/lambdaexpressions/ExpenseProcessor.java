package lambdaexpressions;

@FunctionalInterface
public interface ExpenseProcessor {
	boolean test(Expense expense);

}
