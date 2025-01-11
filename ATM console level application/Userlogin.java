import Notes.Notes;
import java.util.ArrayList;
import java.util.Scanner;

public class Userlogin {// Declaring the User login class, which manages user login and post-login actions.

    static Scanner scan = new Scanner(System.in);// Creating a static Scanner object to take input from the user.

    // Method to handle user login
    public static User userLogin(ArrayList<User> userAccounts) {
        int attempts = 0;// Counter to track the number of login attempts.

        while (attempts < 3) {// Allowing the user up to 3 attempts to log in.

            System.out.print("Enter user ID: ");
            String userId = scan.nextLine();// Storing the entered user ID as a String.

            User matchedUser = null;// Initializing a User object to store the matched account if found.

            // Iterating over the list of user accounts to find a matching user ID.
            for (Accounts account : userAccounts) {
                if (account instanceof User) {// Checking if the account is an instance of the User class.
                    User user = (User) account;// Casting the account object to a User object.
                    if (user.getUserId().equals(userId)) {// Checking if the entered user ID matches this user's ID.
                        matchedUser = user;// Assigning the matched user to matchedUser.
                        break;// Exiting the loop since a match is found.
                    }
                }
            }

            if (matchedUser == null) {// If no matching user is found.
                System.out.println("Invalid User ID! No user found with this ID. Please try again.");
                attempts++;// Incrementing the login attempt counter.

                if (attempts >= 3) {// Checking if the user has exceeded the maximum number of attempts.
                    System.out.println("Too many failed attempts. Exiting.");
                    return null;// Returning null to indicate a failed login.
                }
                continue;// Skipping to the next iteration of the loop.
            }

            if (matchedUser.isLocked()) {// Checking if the matched user account is locked.
                System.out.println("This account is locked. Please contact admin.");
                return null;// Exiting the method if the account is locked.
            }

            System.out.print("Enter PIN: ");
            String password = scan.nextLine();// Storing the entered PIN as a String.

            if (!matchedUser.getPassword().equals(password)) {// Validating the entered PIN against the user's stored password.
                System.out.println("Invalid PIN! Please try again.");
                attempts++;// Incrementing the login attempt counter.

                if (attempts >= 3) {// Checking if the user has exceeded the maximum number of attempts.
                    matchedUser.lockAccount();// Locking the user's account after too many failed attempts.
                    System.out.println("Your account has been locked due to too many failed attempts.");
                    System.out.println("Please try again later or contact admin.");
                    return null;// Returning null to indicate a failed login.
                }
            } else {
                System.out.println("Login Successful!");
                return matchedUser;// Returning the matched user object.
            }
        }

        return null;// Returning null if the user fails to log in within 3 attempts.
    }

    public static void userActions(User account) {// Method to display post-login options and handle user actions.
        if (account == null) {// Checking if the user account is null.
            System.out.println("Invalid user account!");
            return;// Exiting the method for invalid accounts.
        }

        while (true) {// Infinite loop to continuously display options until the user logs out.

            System.out.println("\n1. Check Balance\n2. Withdraw Cash\n3. Deposit Cash\n4. Change PIN\n5. View Transaction History\n6. Logout");
            // Displaying the list of available actions.

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scan.nextLine());// Parsing the user's choice as an integer.

            switch (choice) {// Handling the user's choice using a switch statement.
                case 1:
                    System.out.println("Current Balance: " + account.getBalance());// Displaying the current balance.
                    break;
                case 2:
                    withdrawCash(account);// Calling the withdrawCash method.
                    break;
                case 3:
                    depositCash(account);// Calling the depositCash method.
                    break;
                case 4:
                    changePin(account);// Calling the changePin method.
                    break;
                case 5:
                    viewTransactionHistory(account);// Calling the viewTransactionHistory method.
                    break;
                case 6:
                    System.out.println("Logged out.");
                    return;// Exiting the method.

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }


// Method to withdraw cash from user account
    public static void withdrawCash(User account) { //called directly without creating objects
        if (account == null) {
            System.out.println("Invalid user account!");
            return; // exit if invalid account
        }

        System.out.print("Enter amount to withdraw: ");
        int amount = scan.nextInt(); //get amount as input
        scan.nextLine();

        //validate the amount is * 100
        if (amount <= 0 || amount % 100 != 0) {
            System.out.println("Withdrawal amount must be a positive number and in multiples of 100!");
            return;
        }

        // to check the balance is sufficient or not
        if (amount > account.getBalance()) {
            System.out.println("Insufficient account balance!");
            return;
        }

        // Check if the ATM has sufficient funds
        if (amount > ATM.getAtmBalance()) {
            System.out.println("ATM does not have sufficient funds!");
            return;
        }

        int remainingAmount = amount; // declare this variable to distribute amount
        int total2000Notes = 0, total500Notes = 0, total200Notes = 0, total100Notes = 0;
        //variable to distribute notes

        //to calculate the distributed notes
        for (Notes note : ATM.getAtmNotes()) {
            int noteValue = note.getNote(); // get the denomination value
            int availableNotes = note.getCount();// get the notes count

            if (remainingAmount >= noteValue && availableNotes > 0) {
                int notesToDeduct = Math.min(remainingAmount / noteValue, availableNotes); // no.of notes to deduct
                remainingAmount -= notesToDeduct * noteValue;// deduct withdrawn amount
                note.setCount(availableNotes - notesToDeduct);//to update note count

                // notes counts to deduct
                if (noteValue == 2000)
                    total2000Notes += notesToDeduct;
                if (noteValue == 500)
                    total500Notes += notesToDeduct;
                if (noteValue == 200)
                    total200Notes += notesToDeduct;
                if (noteValue == 100)
                    total100Notes += notesToDeduct;
            }
        }

        //if amt isn't > 0, the request can't fulfil
        if (remainingAmount < 0) {
            System.out.println("ATM does not have enough denominations to fulfill this request.");
            return;
        }

        //update user balance
        account.withdraw(amount);
        ATM.setAtmBalance(ATM.getAtmBalance() - amount);

        //store the transaction
        Transaction transaction = new Transaction(Accounts.getUserId(), "Withdraw", amount);
        Accounts.getTransactionHistory().add(transaction);

        //display notes distributed
        System.out.println("Withdrawal successful! Amount distributed as:");

        if (total2000Notes > 0)
            System.out.println("2000 x " + total2000Notes);
        else
            System.out.println("2000 x 0");

        if (total500Notes > 0)
            System.out.println("500 x " + total500Notes);
        else
            System.out.println("500 x 0");

        if (total200Notes > 0)
            System.out.println("200 x " + total200Notes);
        else
            System.out.println("200 x 0");

        if (total100Notes > 0)
            System.out.println("100 x " + total100Notes);
        else
            System.out.println("100 x 0");

    }


        // Method to deposit cash into the user account
    private static void depositCash(User account) {
        if (account == null) {
            System.out.println("Invalid user account!");
            return; // Exit if invalid account
        }

        System.out.print("Enter amount to deposit: ");
        double amount = scan.nextDouble(); // Get amount as input
        scan.nextLine(); // Consume newline

        if (amount <= 0) {
            System.out.println("Invalid deposit amount!");
            return; // Exit if amount is invalid
        }

        System.out.print("Enter number of 2000 notes: ");
        int atm2000Depo = scan.nextInt();
        System.out.print("Enter number of 500 notes: ");
        int atm500Depo = scan.nextInt();
        System.out.print("Enter number of 200 notes: ");
        int atm200Depo = scan.nextInt();
        System.out.print("Enter number of 100 notes: ");
        int atm100Depo = scan.nextInt();
        scan.nextLine();

        // Calculate total deposit amount
        double totalDepositAmount = atm2000Depo * 2000 + atm500Depo * 500 + atm200Depo * 200 + atm100Depo * 100;

        if (totalDepositAmount != amount) {
            System.out.println("Total deposited amount does not match the expected deposit.");
            return;
        }

        // Update user balance and ATM balance
        account.deposit(amount);
        Transaction transaction = new Transaction(account.getUserId(), "Deposit", totalDepositAmount);
        account.addTransaction(transaction);

        // Update ATM notes
        for (Notes note : ATM.getAtmNotes()) {
            if (Notes.getNote() == 2000) note.setCount(note.getCount() + atm2000Depo);
            if (Notes.getNote() == 500) note.setCount(note.getCount() + atm500Depo);
            if (Notes.getNote() == 200) note.setCount(note.getCount() + atm200Depo);
            if (Notes.getNote() == 100) note.setCount(note.getCount() + atm100Depo);
        }

        System.out.println("Deposit successful! Denominations are:\n" + "2000 x " + atm2000Depo + "\n" + "500 x " + atm500Depo + "\n" + "200 x " + atm200Depo + "\n" + "100 x " + atm100Depo);
    }

    // Method to change PIN
    private static void changePin(User account) {
        System.out.print("Enter current PIN: ");
        String currentPin = scan.nextLine(); // Get current PIN

        if (account.getPin().equals(currentPin)) { // Access pin via instance method
            System.out.print("Enter new PIN: ");
            String newPin = scan.nextLine(); // Get new PIN
            account.setPin(newPin); // Update the user's PIN
            System.out.println("PIN changed successfully!");
        } else {
            System.out.println("Incorrect current PIN!");
        }
    }

    // Method to view transaction history
    private static void viewTransactionHistory(User account) {
        System.out.println("Transaction History:");
        if (Accounts.getTransactionHistory().isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : account.getTransactionHistory()) {
                System.out.println("Performed By: " + transaction.getPerformedBy());
                System.out.println("Type: " + transaction.getType());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("---------------------------");
            }
        }
    }
}