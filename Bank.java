import java.util.Scanner;

interface Transaction {
    void deposit(int amount);
    void withdraw(int amount);
    void display();
}

// Bank class
class Bank {
    int accountNumber;
    double balance;

    // Constructor
    Bank(final int accountNumber, final double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Copy Constructor
    Bank(final Bank obj) {
        this.accountNumber = obj.accountNumber;
        this.balance = obj.balance;
    }

    // Deposit Method
    void deposit(final double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully");
            System.out.println("Updated balance: ₹" + balance);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    // Withdraw Method
    void withdraw(final double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully");
            System.out.println("Updated balance: ₹" + balance);
        } else {
            System.out.println("Invalid transaction: Insufficient balance or invalid amount");
        }
    }

    // Display Account Details
    void display() {
        System.out.println("\n🔹 Account Details:");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
    }
}

// Savings Account (Implements Transaction)
class SavingsAccount implements Transaction {
    private int balance;

    public SavingsAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited ₹" + amount + " into Savings Account. Current Balance: ₹" + balance);
    }

    @Override
    public void withdraw(int amount) {
        if (balance - amount < 500) {
            System.out.println("Withdrawal denied! Minimum balance of ₹500 must be maintained in Savings Account.");
        } else {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount + " from Savings Account. Current Balance: ₹" + balance);
        }
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public void display() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
}

// Current Account (Implements Transaction)
class CurrentAccount implements Transaction {
    private int balance;
    private static final int OVERDRAFT_LIMIT = 5000;

    public CurrentAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited ₹" + amount + " into Current Account. Current Balance: ₹" + balance);
    }

    @Override
    public void withdraw(int amount) {
        if (balance - amount < -OVERDRAFT_LIMIT) {
            System.out.println("Withdrawal denied! Overdraft limit of ₹5000 exceeded.");
        } else {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount + " from Current Account. Current Balance: ₹" + balance);
        }
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public void display() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
}

// Main Class
public class BankSystem {
    public static void main(final String[] args) {
        final Scanner sc = new Scanner(System.in);

        // Account Type Selection
        System.out.println("Enter account type (Savings/Current):");
        final String accType = sc.next();

        System.out.println("Enter Account Number:");
        final int accNo = sc.nextInt();

        System.out.println("Enter Initial Balance:");
        final double balance = sc.nextDouble();

        // Create the account based on type
        SavingsAccount acc1;
        if (accType.equalsIgnoreCase("Savings")) {
            acc1 = new SavingsAccount((int) balance);
        } 
         else {
            System.out.println("Invalid account type! Exiting...");
            sc.close();
            return;
        }
        CurrentAccount acc2;
        if (accType.equalsIgnoreCase("Savings")) {
            acc1 = new SavingsAccount((int) balance);
        } else if (accType.equalsIgnoreCase("Current")) {
            acc2= new CurrentAccount((int) balance);
        } else {
            System.out.println("Invalid account type! Exiting...");
            sc.close();
            return;
        }

        acc1.display();

        while (true) {
            // User menu
            System.out.println("\n🔹 Choose an option:");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Display Account Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter deposit amount:");
                    double depositAmount = sc.nextDouble();
                    acc1.deposit(depositAmount);
                    break;

                case 2:
                    System.out.println("Enter withdrawal amount:");
                    double withdrawAmount = sc.nextDouble();
                    acc1.withdraw(withdrawAmount);
                    break;

                case 3:
                    acc1.display();
                    break;

                case 4:
                    System.out.println("Exiting the system...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    @Override
    public String toString() {
        return "BankSystem []";
    }
}
