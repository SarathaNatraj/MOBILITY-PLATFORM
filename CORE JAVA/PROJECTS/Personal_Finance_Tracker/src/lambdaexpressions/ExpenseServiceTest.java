package lambdaexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseServiceTest {

    private ExpenseService expenseService;

    @BeforeEach
    public void setUp() {
        expenseService = new ExpenseService();  // Initialize the service
    }

    @Test
    public void testFilterExpensesByCategory() {
        // Arrange: Set up test data
        Expense expense1 = new Expense("Grocery shopping", 50.0, "Groceries");
        Expense expense2 = new Expense("Movie tickets", 30.0, "Entertainment");
        Expense expense3 = new Expense("Electric bill", 75.0, "Utilities");
        Expense expense4 = new Expense("Dining out", 40.0, "Groceries");

        List<Expense> expenses = Arrays.asList(expense1, expense2, expense3, expense4);

        // Act: Call the method to test
        List<Expense> filteredExpenses = expenseService.filterExpensesByCategory(expenses, "Groceries");

        // Assert: Verify the result
        assertEquals(2, filteredExpenses.size(), "There should be 2 'Groceries' expenses.");
        assertEquals("Grocery shopping", filteredExpenses.get(0).getDescription());
        assertEquals("Dining out", filteredExpenses.get(1).getDescription(), "Second expense should be 'Dining out'");
    }

    @Test
    public void testFilterExpensesByNonExistentCategory() {
        // Arrange: Set up test data
        Expense expense1 = new Expense("Grocery shopping", 50.0, "Groceries");
        Expense expense2 = new Expense("Movie tickets", 30.0, "Entertainment");
        List<Expense> expenses = Arrays.asList(expense1, expense2);

        // Act: Call the method to test
        List<Expense> filteredExpenses = expenseService.filterExpensesByCategory(expenses, "Travel");

        // Assert: Verify the result
        assertEquals(0, filteredExpenses.size(), "No expenses should match the 'Travel' category.");
    }
}

