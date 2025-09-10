package cs250.exercises;

public class Account extends AccountActions {
    public Account(String name, double balance) {
        super(name, balance);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }

    }

    @Override
    public void withdraw(double amount) {
        if (0 < amount && amount <= balance) {
            this.balance -= amount;
        }

    }
}

abstract class AccountActions {
    protected String name;
    protected double balance;

    protected AccountActions(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    protected abstract String getName();

    protected abstract double getBalance();

    // make sure to check if the deposit is > 0
    protected abstract void deposit(double amount);

    // make sure to check if 0 < amount <= balance
    protected abstract void withdraw(double amount);

    public String toString() {
        return getName() + ":" + getBalance();
    }
}