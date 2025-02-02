class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited " + amount + ". Current balance: " + balance);
        } else {
            System.out.println("Deposit amount must be greater than 0.");
        }
    }

    public synchronized void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ". Current balance: " + balance);
        } else if (amount > 0) {
            System.out.println(Thread.currentThread().getName() + " failed to withdraw " + amount + ". Insufficient funds.");
        } else {
            System.out.println("Withdrawal amount must be greater than 0.");
        }
    }

    public double getBalance() {
        return balance;
    }
}

class CustomerThread extends Thread {
    private BankAccount account;
    private boolean deposit;
    private double amount;

    public CustomerThread(BankAccount account, boolean deposit, double amount) {
        this.account = account;
        this.deposit = deposit;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (deposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}

public class Main2 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); 

        
        Thread t1 = new CustomerThread(account, true, 500);   
        Thread t2 = new CustomerThread(account, false, 200);  
        Thread t3 = new CustomerThread(account, true, 300);   
        Thread t4 = new CustomerThread(account, false, 600);  
        Thread t5 = new CustomerThread(account, true, 100);   

        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}