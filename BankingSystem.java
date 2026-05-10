import java.util.ArrayList;
import java.util.Scanner;

// ---------------- BANK ACCOUNT CLASS ----------------
class BankAccount {

    private int accountNumber;
    private String accountHolderName;
    private double balance;

    // Constructor
    public BankAccount(int accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    // Getter Methods
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit Method
    public void deposit(double amount) {

        if (amount > 0) {

            balance += amount;

            System.out.println("₹" + amount + " Deposited Successfully.");

        } else {
            System.out.println("Invalid Deposit Amount.");
        }
    }

    // Withdraw Method
    public void withdraw(double amount) {

        if (amount > balance) {

            System.out.println("Insufficient Balance.");

        } else if (amount <= 0) {

            System.out.println("Invalid Withdrawal Amount.");

        } else {

            balance -= amount;

            System.out.println("₹" + amount + " Withdrawn Successfully.");
        }
    }

    // Display Account Details
    public void displayAccountDetails() {

        System.out.println("\n----- ACCOUNT DETAILS -----");

        System.out.println("Account Number : " + accountNumber);

        System.out.println("Account Holder : " + accountHolderName);

        System.out.println("Balance        : ₹" + balance);
    }
}

// ---------------- BANK CLASS ----------------
class Bank {

    private ArrayList<BankAccount> accounts = new ArrayList<>();

    // Create Account
    public void createAccount(int accNo, String name, double balance) {

        BankAccount account = new BankAccount(accNo, name, balance);

        accounts.add(account);

        System.out.println("Account Created Successfully.");
    }

    // Find Account
    public BankAccount findAccount(int accNo) {

        for (BankAccount account : accounts) {

            if (account.getAccountNumber() == accNo) {

                return account;
            }
        }

        return null;
    }

    // Display All Accounts
    public void displayAllAccounts() {

        if (accounts.isEmpty()) {

            System.out.println("No Accounts Found.");

            return;
        }

        for (BankAccount account : accounts) {

            account.displayAccountDetails();
        }
    }
}

// ---------------- MAIN CLASS ----------------
public class BankingSystem {

    // SAFE INTEGER INPUT METHOD
    public static int getIntInput(Scanner sc, String message) {

        int value;

        while (true) {

            System.out.print(message);

            if (sc.hasNextInt()) {

                value = sc.nextInt();

                sc.nextLine(); // clear buffer

                return value;

            } else {

                System.out.println("Invalid Input. Enter numbers only.");

                sc.nextLine();
            }
        }
    }

    // SAFE DOUBLE INPUT METHOD
    public static double getDoubleInput(Scanner sc, String message) {

        double value;

        while (true) {

            System.out.print(message);

            if (sc.hasNextDouble()) {

                value = sc.nextDouble();

                sc.nextLine(); // clear buffer

                return value;

            } else {

                System.out.println("Invalid Input. Enter valid amount.");

                sc.nextLine();
            }
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Bank bank = new Bank();

        int choice;

        do {

            System.out.println("\n====== BANKING SYSTEM ======");

            System.out.println("1. Create Account");

            System.out.println("2. Deposit Money");

            System.out.println("3. Withdraw Money");

            System.out.println("4. Check Account Details");

            System.out.println("5. Display All Accounts");

            System.out.println("6. Exit");

            choice = getIntInput(sc, "Enter Choice: ");

            switch (choice) {

                case 1:

                    int accNo = getIntInput(sc, "Enter Account Number: ");

                    System.out.print("Enter Account Holder Name: ");

                    String name = sc.nextLine();

                    double balance = getDoubleInput(sc, "Enter Initial Balance: ");

                    bank.createAccount(accNo, name, balance);

                    break;

                case 2:

                    int depositAccNo = getIntInput(sc, "Enter Account Number: ");

                    BankAccount depositAccount = bank.findAccount(depositAccNo);

                    if (depositAccount != null) {

                        double depositAmount = getDoubleInput(sc, "Enter Amount to Deposit: ");

                        depositAccount.deposit(depositAmount);

                    } else {

                        System.out.println("Account Not Found.");
                    }

                    break;

                case 3:

                    int withdrawAccNo = getIntInput(sc, "Enter Account Number: ");

                    BankAccount withdrawAccount = bank.findAccount(withdrawAccNo);

                    if (withdrawAccount != null) {

                        double withdrawAmount = getDoubleInput(sc, "Enter Amount to Withdraw: ");

                        withdrawAccount.withdraw(withdrawAmount);

                    } else {

                        System.out.println("Account Not Found.");
                    }

                    break;

                case 4:

                    int checkAccNo = getIntInput(sc, "Enter Account Number: ");

                    BankAccount checkAccount = bank.findAccount(checkAccNo);

                    if (checkAccount != null) {

                        checkAccount.displayAccountDetails();

                    } else {

                        System.out.println("Account Not Found.");
                    }

                    break;

                case 5:

                    bank.displayAllAccounts();

                    break;

                case 6:

                    System.out.println("Thank You for Using Banking System.");

                    break;

                default:

                    System.out.println("Invalid Choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}