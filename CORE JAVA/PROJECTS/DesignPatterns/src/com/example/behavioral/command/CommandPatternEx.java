package com.example.behavioral.command;

public class CommandPatternEx {
    public static void main(String[] args) {
        Account account = new Account(1000.0);
        TransactionManager manager = new TransactionManager();

        Command addIncome = new AddIncomeCommand(account, 500.0);
        Command recordExpense = new RecordExpenseCommand(account, 200.0);

        // Execute Commands
        manager.executeCommand(addIncome);  // Added: 500.0, New Balance: 1500.0
        manager.executeCommand(recordExpense);  // Deducted: 200.0, New Balance: 1300.0

        // Undo Commands
        manager.undoLastCommand();  // Added: 200.0, New Balance: 1500.0
        manager.undoLastCommand();  // Deducted: 500.0, New Balance: 1000.0
    }
}
