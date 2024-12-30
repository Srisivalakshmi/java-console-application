import java.util.ArrayList;

public class Admin {
    static ArrayList<Transaction> allTransactionHistory = new ArrayList<>();

    private static String id = "admin123";
    private String password = "pass123";

    public static String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    static void start() {
        System.out.println("ATM Console Application");
    }
}
