import java.util.ArrayList;

public class Admin {
    static ArrayList<Transaction> allTransactionHistory = new ArrayList<>(); // to store all admin transactions
    private static String id = "admin123"; // Admin ID
    private String password = "pass123"; // Admin password

    // Getter to get admin ID
    public static String getId() {
        return id;
    }

    // Getter to get admin password
    public String getPassword() {
        return password;
    }

    // Getter to return the list of all admin transactions
    public static ArrayList<Transaction> getAllTransactionHistory() {
        return allTransactionHistory;
    }
}
