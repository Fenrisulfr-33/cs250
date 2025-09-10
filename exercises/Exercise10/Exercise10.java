package cs250.exercises;

public class Exercise10 {
        public static void main(String[] args) {
                Bank bank = new Bank();
                Account account1 = new Account("Archer", 0);
                Account account2 = new Account("Adam", 0);
                Account account3 = new Account("Eric", 0);
                bank.addAccount(account1);
                bank.addAccount(account2);
                bank.addAccount(account3);
                bank.findAccount("Archer").deposit(1000);
                bank.findAccount("Adam").deposit(500);
                bank.findAccount("Eric").deposit(500);
                bank.findAccount("Archer").withdraw(10);
                bank.findAccount("Eric").withdraw(10);
                bank.findAccount("Adam").withdraw(100);
                System.out.println(bank.findAccount("Archer").getBalance());
                System.out.println(bank.findAccount("Eric").getBalance());
                System.out.println(bank.findAccount("Adam").getBalance());
                bank.listAccounts();
        }
}