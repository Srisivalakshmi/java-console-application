import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ATM {
    static Scanner scan = new Scanner(System.in); // Create scanner object to take user inputs
    static ArrayList<User> userAccounts = new ArrayList<>(); // List to store user accounts
    static double atmBalance = 50000.0; // Initial ATM balance
    static List<Notes> atmNotes = new ArrayList<>(Arrays.asList(new TwoThous(2000, 0), new FiveHun(500, 0), new TwoHun(200, 0), new OneHun(100, 0)));
    // List of ATM notes and their counts

    // Start method to run the main ATM application
    public static void start() {
        while (true) {
            // to display the main options
            System.out.println("\n1. Admin Login\n2. User Login\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt(); // get user choice
            scan.nextLine();

            switch (choice) {
                case 1:
                    Admin admin = Adminlogin.adminLogin(); // Call admin login method
                    if (admin != null) {
                        Adminlogin.adminAction(); // Call admin action methods after login successful
                    }
                    break;
                case 2:
                    User user = Userlogin.userLogin(); // Call user login method
                    if (user != null) {
                        Userlogin.userActions(user); // Call user actions after login successful
                    }
                    break;
                case 3:
                    System.out.println("Exiting the application.");
                    return; // to exit the program
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Getter to return user accounts list
    public static ArrayList<User> getUserAccounts() {
        return userAccounts;
    }

    // Getter to return the current ATM balance
    public static double getAtmBalance() {
        return atmBalance;
    }

    // Setter to update the ATM balance
    public static void setAtmBalance(double newBalance) {
        atmBalance = newBalance;
    }

    // Getter to return the list of ATM notes
    public static List<Notes> getAtmNotes() {
        return atmNotes;
    }
}
