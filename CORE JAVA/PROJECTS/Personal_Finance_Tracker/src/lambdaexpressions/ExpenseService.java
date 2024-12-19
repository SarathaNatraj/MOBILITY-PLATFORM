package lambdaexpressions;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseService {
    
    public List<Expense> filterExpensesByCategory(List<Expense> expenses, String category) {
        return expenses.stream()
                       .filter(expense -> expense.getCatagory().equalsIgnoreCase(category))
                       .collect(Collectors.toList());
    }
}
