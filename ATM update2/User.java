import java.util.ArrayList;

public class User {

    //static is used to store first
    private static String userId; // var to store user id
    private String pin;// var to store pin
    private static double balance;// var to store balance
    private boolean isLocked = false; // var to lock account
    private static ArrayList<Transaction> transactionHistory = new ArrayList<>();
    //list to store transaction history

    // Constructor to initialize new User object with userId, PIN, and balance.
    public User(String userId, String pin, double balance) {
        this.userId = userId; // Assigns the userId passed as parameter to static variable.
        this.pin = pin;// Assigns the pin passed as parameter to static variable.
        this.balance = balance;// Assigns the balance passed as parameter to static variable.
    }

    // getter for user id
    public static String getUserId() {
        return userId;
    }

    // getter for pin
    public String getPin() {
        return pin;
    }

    // setter to update pin
    public void setPin(String pin) {
        this.pin = pin;
    }

    // getter to balance
    public static double getBalance() {
        return balance;
    }

    //method to deposit into user account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;//add deposit to balance
        }
    }

    //method to withdraw from user account
    public static boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;// subtract withdraw from balance
            return true; //return true for successful withdraw
        }
        return false;// unsuccessful withdrawal
    }

    //getter to get transaction history
    public static ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    //method to check account is locked
    public boolean isLocked() {
        return isLocked;
    }

    //method to lock user account
    public void lockAccount() {
        this.isLocked = true;// to set this var as true
    }
}

