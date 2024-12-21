package com.example.behavioral.command;

class RecordExpenseCommand implements Command {
    private Account account;
    private double amount;

    public RecordExpenseCommand(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.deduct(amount);
    }

    @Override
    public void undo() {
        account.add(amount);
    }
}