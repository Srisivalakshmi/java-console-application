import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<User> userAccounts = new ArrayList<>();
    static double atmBalance = 50000.0;
    static List<Notes> atmNotes = new ArrayList<>();

    static {
        userAccounts.add(new User("sri", "2345", 10000.0));

        atmNotes.add(new TwoThous(2000, 50));
        atmNotes.add(new FiveHun(500, 100));
        atmNotes.add(new TwoHun(200, 100));
        atmNotes.add(new OneHun(100, 100));
    }

    public static void login() {
        while (true) {
            System.out.println("\n1. Admin Login\n2. User Login\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    Adminlogin.adminLogin();
                    break;
                case 2:
                    Userlogin.userLogin();
                    break;
                case 3:
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    public static ArrayList<User> getUserAccounts() {
        return userAccounts;
    }

    public static double getAtmBalance() {
        return atmBalance;
    }

    public static void setAtmBalance(double newBalance) {
        atmBalance = newBalance;
    }

    public static List<Notes> getAtmNotes() {
        return atmNotes;
    }

    public static void processTransaction(String performedBy, String type, double amount) {
        if (type.equalsIgnoreCase("Withdraw")) {
            if (atmBalance >= amount) {
                atmBalance -= amount;
                Admin.allTransactionHistory.add(new Transaction(performedBy, "Withdraw", amount));
                System.out.println("Withdrawal successful! ATM Balance updated to: " + atmBalance);
            } else {
                System.out.println("ATM doesn't have enough funds for this transaction.");
            }
        } else if (type.equalsIgnoreCase("Deposit")) {
            atmBalance += amount;
            Admin.allTransactionHistory.add(new Transaction(performedBy, "Deposit", amount));
            System.out.println("Deposit successful! ATM Balance updated to: " + atmBalance);
        } else {
            System.out.println("Invalid transaction type!");
        }
    }
}
