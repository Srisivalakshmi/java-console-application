import java.util.ArrayList;
import java.util.Scanner;

public class Adminlogin {
    static Scanner scan = new Scanner(System.in); // Scanner for user inputs
    private static ArrayList<User> userList = new ArrayList<>(); // List of all users

    // Method of admin login
    public static Admin adminLogin() { //called directly without creating objects
        Admin admin = new Admin();
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
