import java.util.ArrayList;
import java.util.Scanner;

public class Adminlogin {
    static Scanner scan = new Scanner(System.in); // Scanner for user inputs
    private static ArrayList<User> userList = new ArrayList<>(); // List of all users

    // Method of admin login
    public static Admin adminLogin() { //called directly without creating objects
        Admin admin = new Admin();
   import Notes.Notes;
import java.util.ArrayList;
import java.util.Scanner;

public class Adminlogin {// Declaring a public class named Admin login.

    static Scanner scan = new Scanner(System.in);// Creating a static Scanner object for taking user input.

    public static Admin adminLogin(ArrayList<User> accounts) {// Method to handle admin login, accepting a list of user accounts as input.

        for (int attempts = 0; attempts < 3; attempts++) {// Allowing up to 3 login attempts.
            System.out.print("Enter admin ID: ");
            String id = scan.nextLine();// Reading the admin ID input.

            Admin matchedAdmin = null;// Variable to store the matched admin account.

            // Search for the matching Admin account
            for (Accounts account : accounts) {// Iterating through all accounts.

                if (account instanceof Admin admin) {// Checking if the current account is an instance of Admin.

                    if (id.equals(admin.getId())) {// Checking if the entered ID matches the admin ID.
                        matchedAdmin = admin;// Storing the matched admin account.
                        break;// Exiting the loop once a match is found.
                    }
                }
            }

            if (matchedAdmin == null) {// If no matching admin account is found.
                System.out.println("Invalid Admin ID! Try again.");
                continue;// Retry login attempt.
            }

            System.out.print("Enter admin password: ");
            String password = scan.nextLine();// Reading the admin password input.
            if (password.equals(matchedAdmin.getPassword())) {// Checking if the entered password matches.
                System.out.println("Admin Login Successful!");
                return matchedAdmin;// Returning the matched admin account on successful login.
            } else {
                System.out.println("Invalid password! Try again.");
            }
        }

        System.out.println("Too many failed attempts. Access denied.");// Displaying a message after 3 failed attempts.

        return null;// Returning null if login fails.
    }

    // Admin actions after login
    public static void adminAction(Admin admin) {// Method to display and execute admin actions after successful login.

        while (true) {// Infinite loop to repeatedly display admin options until logout.
            System.out.println("\n1. Add User\n2. Delete User\n3. Deposit to ATM\n4. View All Users\n5. View All Transactions\n6. Check ATM Balance\n7. Logout");
            // Displaying the admin options.

            System.out.print("Enter your choice: ");// Prompting the admin to enter a choice.
            int choice = scan.nextInt();// Reading the admin choice.
            scan.nextLine();

            switch (choice) {// Handling the admin choice using a switch statement.
                case 1:
                    addUser();// Calling the addUser method.
                    break;
                case 2:
                    deleteUser();// Calling the deleteUser method.
                    break;
                case 3:
                    depositToATM();// Calling the depositToATM method.
                    break;
                case 4:
                    viewAllUsers();// Calling the viewAllUsers method.
                    break;
                case 5:
                    viewAllTransactions();// Calling the viewAllTransactions method.
                    break;
                case 6:
                    checkATMBalance();// Calling the checkATMBalance method.
                    break;
                case 7:
                    System.out.println("Logged out.");
                    return;// Exiting the method.
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void addUser() {// Method to add a new user.

        System.out.print("Enter new user ID: ");
        String userId = scan.nextLine();// Reading the new user ID.

        for (Accounts account : ATM.getUserAccounts()) {// Iterating through user accounts to check for duplicate IDs.

            if (account instanceof User user && user.getUserId().equals(userId)) {// Checking if the user ID already exists.

                System.out.println("User ID already exists! Please choose a different ID.");
                return;// Exiting the method.
            }
        }

        System.out.print("Enter initial PIN: ");
        String pin = scan.nextLine();// Reading the PIN.

        System.out.print("Enter initial password: ");
        String password = scan.nextLine();// Reading the password.

        System.out.print("Enter initial balance: ");
        double balance = scan.nextDouble();// Reading the balance.
        scan.nextLine();

        ATM.getUserAccounts().add(new User(userId, pin, password, balance));// Adding the new user to the list of user accounts.

        System.out.println("User added successfully!");
    }

    private static User findUserById(String userId) {// Method to find a user by their ID.

        for (Accounts account : ATM.getUserAccounts()) {// Iterating through user accounts.

            if (account instanceof User user && user.getUserId().equals(userId)) {// Checking if the current account matches the user ID.
                return user;// Returning the matched user account.
            }
        }
        return null;// Returning null if no match is found.
    }

    static void deleteUser() {// Method to delete a user.
        System.out.print("Enter user ID to delete: ");// Prompting for the user ID to delete.
        String userId = scan.nextLine();// Reading the user ID.

        User userToRemove = findUserById(userId);// Finding the user by their ID.

        if (userToRemove != null) {// If the user is found.
            ATM.getUserAccounts().remove(userToRemove);// Removing the user from the list of user accounts.
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    static void depositToATM() {// Method to deposit money into the ATM.
        int atm2000 = Admin.getValidInt("Enter number of 2000 notes to deposit: ");// Prompting for the number of 2000 denomination notes.
        int atm500 = Admin.getValidInt("Enter number of 500 notes to deposit: ");// Prompting for the number of 500 denomination notes.
        int atm200 = Admin.getValidInt("Enter number of 200 notes to deposit: ");// Prompting for the number of 200 denomination notes.
        int atm100 = Admin.getValidInt("Enter number of 100 notes to deposit: ");// Prompting for the number of 100 denomination notes.

        double totalDepositAmount = atm2000 * 2000 + atm500 * 500 + atm200 * 200 + atm100 * 100;// Calculating the total deposit amount.

        for (Notes note : ATM.getAtmNotes()) {// Iterating through ATM notes.
            if (note.getDenomination() == 2000) {// If the note is of 2000 denomination.
                note.setCount(note.getCount() + atm2000);// Updating the count for 2000 notes.
            } else if (note.getDenomination() == 500) {// If the note is of 500 denomination.
                note.setCount(note.getCount() + atm500);// Updating the count for 500 notes.
            }
            else if (note.getDenomination() == 200) {// If the note is of 200 denomination.
                note.setCount(note.getCount() + atm200);// Updating the count for 200 notes.
            }
            else if (note.getDenomination() == 100) {// If the note is of 100 denomination.
                note.setCount(note.getCount() + atm100);// Updating the count for 100 notes.
            }
        }

        ATM.setAtmBalance(ATM.getAtmBalance() + totalDepositAmount);// Updating the ATM balance with the deposit amount.
        System.out.println("Deposit successful!");
        System.out.println("Updated ATM Balance: " + ATM.getAtmBalance());// Displaying the updated ATM balance.
    }

    static void viewAllUsers() {// Method to view all users.
        System.out.println("All Users:");
        for (Accounts account : ATM.getUserAccounts()) {// Iterating through user accounts.
            if (account instanceof User user) {// Checking if the current account is a User.
                System.out.println("User ID: " + user.getUserId());// Displaying the user ID.
            }
        }
    }

    static void viewAllTransactions() {// Method to view all transactions.
        System.out.println("All Transaction History:");
        for (Transaction transaction : Accounts.getTransactionHistory()) {// Iterating through transaction history stored in Accounts.
            System.out.println("Performed by: " + transaction.getPerformedBy());// Displaying who performed the transaction.
            System.out.println("Type: " + transaction.getType());// Displaying the type of transaction (e.g., deposit, withdrawal).
            System.out.println("Amount: " + transaction.getAmount());// Displaying the transaction amount.
            System.out.println("---------------------------");
        }

        for (Transaction transaction : Accounts.getAllTransactionHistory()) {// Iterating through all transaction history.
            System.out.println("Performed By: " + transaction.getPerformedBy());// Displaying who performed the transaction.
            System.out.println("Type: " + transaction.getType());// Displaying the type of transaction.
            System.out.println("Amount: " + transaction.getAmount());// Displaying the transaction amount.
            System.out.println("---------------------------");
        }
    }

    static void checkATMBalance() {// Method to check the ATM balance.
        System.out.println("ATM Balance: " + ATM.getAtmBalance());// Displaying the current ATM balance.
    }
}

     // Try up to 3 attempts for admin login
        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.print("Enter admin ID: ");
            String id = scan.nextLine(); // get admin ID input

            // to check the entered ID matches admin ID
            if (!id.equals(Admin.getId())) {
                System.out.println("Invalid Admin ID! Try again.");
                continue; // Continue to the next attempt
            }

            System.out.print("Enter admin password: ");
            String password = scan.nextLine(); // get password input

            // to check the entered password is correct
            if (password.equals(admin.getPassword())) {
                System.out.println("Admin Login Successful!");
                return admin; // Return admin object if login is successful
            } else {
                System.out.println("Invalid password! Try again.");
            }
        }
        System.out.println("Too many failed attempts. Access denied.");
        return null; // Return null if login fails after 3 attempts
    }

    // Method for admin actions after login
    public static void adminAction() { //called directly without creating objects
        while (true) {
            // Display the admin options
            System.out.println("\n1. Add User\n2. Delete User\n3. Deposit to ATM\n4. View All Users\n5. View All Transactions\n6. Check ATM Balance\n7. Logout");
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt(); // get admin choice
            scan.nextLine(); 

            switch (choice) {
                case 1:
                    addUser(); // Add new user
                    break;
                case 2:
                    deleteUser(); // Delete user
                    break;
                case 3:
                    depositToATM(); // Deposit to ATM
                    break;
                case 4:
                    viewAllUsers(); // View all users
                    break;
                case 5:
                    viewAllTransactions(); // View all transactions
                    break;
                case 6:
                    checkATMBalance(); // Check ATM balance
                    break;
                case 7:
                    System.out.println("Logged out.");
                    return; // Return to main options
                default:
                    System.out.println("Invalid choice! Try again."); // Invalid choice
            }
        }
    }

    // Method to add a new user
    static void addUser() { //called directly without creating objects
        System.out.print("Enter new user ID: ");
        String userId = scan.nextLine(); // get new user ID input

        for (User user : userList) { // to avoid repeating userID
            if (user.getUserId().equals(userId)) {
                System.out.println("User ID already exists! Please choose a different ID.");
                return;
            }
        }

        System.out.print("Enter initial PIN: ");
        String pin = scan.nextLine(); // get initial PIN input

        System.out.print("Enter initial balance: ");
        double balance = scan.nextDouble(); // get initial balance input
        scan.nextLine();

        // Add the new user to the user accounts list
        ATM.userAccounts.add(new User(userId, pin, balance));
        System.out.println("User added successfully!");
    }

    // Method to delete an existing user
    public static void deleteUser() { //called directly without creating objects
        System.out.print("Enter user ID to delete: ");
        String userId = scan.nextLine(); // get user ID input to delete

        User userToRemove = null;
        // to find the user to delete from the list
        for (User user : userList) {
            if (user.getUserId().equals(userId)) {
                userToRemove = user;
                break; // User found and exit the loop
            }
        }

        if (userToRemove != null) {
            userList.remove(userToRemove); // Remove the user from the list
            System.out.println("User deleted successfully!");
        }
        else {
            System.out.println("User not found!"); // User not found message
        }
    }

    // Method to deposit money into the ATM
    static void depositToATM() { //called directly without creating objects
        System.out.print("Enter number of 2000 notes to deposit: ");
        int atm2000 = scan.nextInt(); // get the no. of 2000 notes input
        System.out.print("Enter number of 500 notes to deposit: ");
        int atm500 = scan.nextInt(); // get the no. of 500 notes input
        System.out.print("Enter number of 200 notes to deposit: ");
        int atm200 = scan.nextInt(); // get the no. of 200 notes input
        System.out.print("Enter number of 100 notes to deposit: ");
        int atm100 = scan.nextInt(); // get the no. of 100 notes input
        scan.nextLine();

        // to calculate the total deposit amount
        double totalDepositAmount = atm2000 * 2000 + atm500 * 500 + atm200 * 200 + atm100 * 100;

        // Update the notes count in ATM
        for (Notes note : ATM.getAtmNotes()) {
            if (note.getNote() == 2000) {
                note.setCount(note.getCount() + atm2000);
            }
            else if (note.getNote() == 500) {
                note.setCount(note.getCount() + atm500);
            }
            else if (note.getNote() == 200) {
                note.setCount(note.getCount() + atm200);
            }
            else if (note.getNote() == 100) {
                note.setCount(note.getCount() + atm100);
            }
        }

        // Update the ATM balance
        ATM.setAtmBalance(ATM.getAtmBalance() + totalDepositAmount);
        // to display the transaction history of admin
        Admin.allTransactionHistory.add(new Transaction("Admin", "Deposit to ATM", totalDepositAmount));

        System.out.println("Deposit successful!");
        System.out.println("Updated ATM Balance: " + ATM.getAtmBalance()); // Display updated ATM balance
    }

    // Method to view all users
    public static void viewAllUsers() { //called directly without creating objects
        System.out.println("All Users:");
        // for loop to display all user IDs
        for (User account : ATM.userAccounts) {
            System.out.println("User ID: " + account.getUserId());
        }
    }

    // Method to view all transactions
    static void viewAllTransactions() { //called directly without creating objects
        System.out.println("All Transaction History:");

        boolean adminTransactionFound = false;
        // to display all admin transactions
        for (Transaction transaction : Admin.getAllTransactionHistory()) {
            if ("Deposit to ATM".equalsIgnoreCase(transaction.getType())) {
                adminTransactionFound = true;
                System.out.println("Admin Transaction:");
                System.out.println("Performed by: "+ transaction.getPerformedBy());
                System.out.println("Type: " + transaction.getType());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("---------------------------");
            }
        }
        if (!adminTransactionFound) {
            System.out.println("No Admin transactions found.");
        }

        // to display all user transactions
        if (User.getTransactionHistory().isEmpty()) {
            System.out.println("No User transactions found.");
        }
        else {
            for (Transaction transaction : User.getTransactionHistory()) {
                if ("Withdraw".equalsIgnoreCase(transaction.getType()) || "Deposit".equalsIgnoreCase(transaction.getType())) {
                    System.out.println("User Transaction:");
                    System.out.println("Performed by: " + transaction.getPerformedBy());
                    System.out.println("Type: " + transaction.getType());
                    System.out.println("Amount: " + transaction.getAmount());
                    System.out.println("---------------------------");
                }
            }
        }
    }

    // Method to check the ATM balance
    static void checkATMBalance() { //called directly without creating objects
        System.out.println("ATM Balance: " + ATM.getAtmBalance());
    }
}
