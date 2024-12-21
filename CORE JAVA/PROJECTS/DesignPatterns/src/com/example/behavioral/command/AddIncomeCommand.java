package com.example.behavioral.command;

class AddIncomeCommand implements Command {
    private Account account;
    private double amount;

    public AddIncomeCommand(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.add(amount);
    }

    @Override
    public void undo() {
        account.deduct(amount);
    }
}
