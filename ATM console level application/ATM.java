import ListofNotes.FiveHun;
import ListofNotes.OneHun;
import ListofNotes.TwoHun;
import ListofNotes.TwoThous;
import Notes.Notes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ATM {// Declaring a public class named ATM.

    static Scanner scan = new Scanner(System.in);// Creating a static Scanner object to take user input throughout the program.

    static ArrayList<User> userAccounts = new ArrayList<>();// Declaring a static ArrayList to store user account details.
    static double atmBalance = 50000.0;// Initializing a static variable to hold the initial balance of the ATM.
    static List<Notes> atmNotes = new ArrayList<>(Arrays.asList(new TwoThous(2000, 0), new FiveHun(500, 0), new TwoHun(200, 0), new OneHun(100, 0)));
    // Declaring a static list to store ATM notes and their counts, initialized with zero counts.

    public static void start() {// Public static method that serves as the starting point for the ATM operations.

        while (true) {// Infinite loop to continuously display menu options until the user chooses to exit.
            System.out.println("\n1. Admin Login\n2. User Login\n3. Exit");// Displaying menu options to the user.
            System.out.print("Enter your choice: ");// Prompting the user to enter their choice.
            if (!scan.hasNextInt()) {// Checking if the input is not an integer.
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
                continue;// Skipping the rest of the loop to re-prompt the menu.
            }

            int choice = scan.nextInt();// Reading the user's choice as an integer.
            scan.nextLine();

            if (choice < 1 || choice > 3) {// Validating if the choice is outside the valid range (1-3).
                System.out.println("Invalid choice! Please select a number between 1 and 3.");
                // Displaying an error message for out-of-range choices.
                continue;// Skipping the rest of the loop to re-prompt the menu.
            }

            switch (choice) {// Using a switch statement to handle different menu options.
                case 1:
                    Admin admin = Adminlogin.adminLogin(getUserAccounts());
                    // Calling the adminLogin method from the Admin login class and passing user accounts.
                    if (admin != null) {// Checking if the admin login was successful.
                        Adminlogin.adminAction(admin);// Calling the adminAction method to perform admin operations.
                    }
                    break;// Exiting the switch case.

                case 2:
                    User user = Userlogin.userLogin(userAccounts);
                    // Calling the userLogin method from the User login class and passing user accounts.
                    if (user != null) {// Checking if the user login was successful.
                        Userlogin.userActions(user);// Calling the userActions method to perform user operations.
                    }
                    break;// Exiting the switch case.

                case 3:
                    System.out.println("Exiting the application.");// Displaying a message indicating the application is exiting.
                    scan.close();// Closing the Scanner to release resources.
                    return;// Exiting the method and terminating the loop.
            }
        }
    }

    // Getter to return user accounts list
    public static ArrayList<User> getUserAccounts() {
        return userAccounts;// Returning the user accounts list.
    }

    // Getter to return the current ATM balance
    public static double getAtmBalance() {
        return atmBalance;// Returning the ATM balance.
    }

    // Setter to update the ATM balance
    public static void setAtmBalance(double newBalance) {
        atmBalance = newBalance;// Updating the ATM balance with the new value.
    }

    // Getter to return the list of ATM notes
    public static List<Notes> getAtmNotes() {
        return atmNotes; // Returning the list of ATM notes.
    }
}
