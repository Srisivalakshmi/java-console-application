import java.util.ArrayList;

public abstract class Accounts {
    // Declaring an abstract class named Accounts. This class cannot be instantiated and is meant to be extended by other classes.
    private static String userId;// A static variable to store the user ID, shared across all instances of the Accounts class.
    private String pin;// A private instance variable to store the PIN of the account.
    private final String password;// A private final instance variable to store the account's password. It cannot be changed once initialized.
    private static final ArrayList<Transaction> transactionHistory = new ArrayList<>();
    // A static final ArrayList to store the transaction history, shared among all instances.

    // Constructor to initialize the account with userId, pin, and password.
    public Accounts(String userId, String pin, String password) {
        Accounts.userId = userId;// Setting the static userId for the Accounts class.
        this.pin = pin;// Initializing the instance variable pin with the provided value.
        this.password = password;// Initializing the final password variable with the provided value.
    }

    // Getter method for userId.
    public static String getUserId() {
        return userId;// Returning the value of the static userId variable.
    }

    // Getter method for all transaction history (global to the Admin scope).
    public static ArrayList<Transaction> getAllTransactionHistory() {
        return Admin.allTransactionHistory;// Returning the static allTransactionHistory list from the Admin class.
    }

    // Getter method for pin.
    public String getPin() {
        return pin;
        // Returning the value of the instance variable pin.
    }

    // Setter method for pin.
    public void setPin(String pin) {
        this.pin = pin;
        // Updating the value of the instance variable pin.
    }

    // Getter method for password.
    public String getPassword() {
        return password;// Returning the value of the final password variable.
    }

    // Getter method for transaction history (specific to this account).
    public static ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;// Returning the static transactionHistory list shared by all instances of Accounts.
    }

    // Method to add a transaction to the transaction history.
    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);// Adding the provided transaction object to the transactionHistory list.
    }
}
