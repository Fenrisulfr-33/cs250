package cs250.exercises;

import java.util.ArrayList;

public class Bank extends BankActions {
    public Bank() {
        accounts = new ArrayList<>();
    }

    @Override
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    @Override
    public Account findAccount(String name) {
        Account foundAccount = null;
        for (Account account : this.accounts) {
            if (account.getName().equals(name)) {
                foundAccount = account;
                break;
            }
        }
        return foundAccount;
    }

    @Override
    public void listAccounts() {
        for (Account account : this.accounts) {
            System.out.println(account.toString());
        }
    }
}

abstract class BankActions {
    protected ArrayList<Account> accounts;

    public abstract void addAccount(Account account);

    public abstract Account findAccount(String name);

    public abstract void listAccounts();

    public void performTransactions(String name, double amount, String type) {
        Account account = findAccount(name);
        if (account != null) {
            if (type.equals("deposit"))
                account.deposit(amount);
            else if (type.equals("withdraw"))
                account.withdraw(amount);
        }
    }
}
