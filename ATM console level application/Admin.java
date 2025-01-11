import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Accounts {// Declaring a public class named Admin that extends the Accounts class (inherits its properties and methods).
    static Scanner scan = new Scanner(System.in);// Creating a static Scanner object to take user inputs throughout the program.
    static ArrayList<Transaction> allTransactionHistory = new ArrayList<>();
    // Declaring a static ArrayList to store all admin transactions for reference.

    private static final String ADMIN_ID = "admin123";// Declaring a private constant string for the admin ID.
    private static final String ADMIN_PASSWORD = "pass123";// Declaring a private constant string for the admin password.

    // Constructor for Admin
    public Admin(String id, String pin, String password) {// Constructor to initialize an Admin object with id, pin, and password.
        super(id, pin, password);// Calling the parent class (Accounts) constructor to initialize inherited fields.
    }

    public String getId() {// Public method to return the admin ID.
        return ADMIN_ID;// Returning the static admin ID.
    }

    // Getter for admin password (if needed)
    public String getPassword() {// Public method to return the admin password.
        return ADMIN_PASSWORD;// Returning the static admin password.
    }

    // Utility method to get a valid integer input
    static int getValidInt(String prompt) {// Static method to repeatedly prompt the user until a valid non-negative integer is entered.

        while (true) {// Infinite loop to validate input until it meets the criteria.
            System.out.print(prompt);// Displaying the prompt message to the user.
            if (scan.hasNextInt()) {// Checking if the input is a valid integer.
                int value = scan.nextInt();// Reading the input integer.
                scan.nextLine();

                if (value >= 0) {// Checking if the entered value is non-negative.
                    return value;// Returning the valid non-negative integer.
                } else {
                    System.out.println("Please enter a non-negative number.");
                }
            }
            else {
                System.out.println("Invalid input. Please enter a valid number.");// Displaying an error message for invalid input.
                scan.nextLine();
            }
        }
    }
}
