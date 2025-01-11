public class User extends Accounts {
    private double balance; // Stores balance
    private boolean isLocked = false; // Indicates if the account is locked

    // Constructor to initialize User object with userId, pin, password and default balance
    public User(String userId, String pin, String password, double balance) {
        super(userId, pin, password); // Call superclass constructor
        this.balance = 0.0; // Default balance is 0.0
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Deposit method to add amount to balance
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // Withdraw method to subtract amount from balance
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Lock account method
    public void lockAccount() {
        this.isLocked = true;
    }

    // Check if account is locked
    public boolean isLocked() {
        return isLocked;
    }
}