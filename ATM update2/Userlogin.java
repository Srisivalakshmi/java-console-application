import java.util.Scanner;

public class Userlogin {
    static Scanner scan = new Scanner(System.in);// avoids creating multiple Scanner objects

    public static User userLogin() {
        int attempts = 0;

        while (attempts < 3) { // allow 3 attempts for user login
            System.out.print("Enter user ID: ");
            String userId = scan.nextLine(); // get userID as input

            User matchedUser = null; // Initialize the user object to null

            // to check the entered user ID matches an existing user account
            for (User account : ATM.getUserAccounts()) {
                if (account.getUserId().equals(userId)) {
                    matchedUser = account;
                    break;
                }
            }

            if (matchedUser == null) {
                System.out.println("Invalid User ID! No user found with this ID. Please try again.");
                attempts++;
                if (attempts >= 3) {
                    System.out.println("Too many failed attempts. Exiting.");
                    return null; // Exit if reached maximum attempts
                }
                continue; // Continue to the next attempt
            }

            // to check the matched user account is locked
            if (matchedUser.isLocked()) {
                System.out.println("This account is locked. Please contact admin.");
                return null; // exit if account is locked
            }

            System.out.print("Enter PIN: ");
            String pin = scan.nextLine(); // get the PIN as input

            //validate pin
            if (!matchedUser.getPin().equals(pin)) {
                System.out.println("Invalid PIN! Please try again.");
                attempts++;
                if (attempts >= 3) {
                    matchedUser.lockAccount(); //lock the account after 3 attempts failed
                    System.out.println("Your account has been locked due to too many failed attempts.");
                    System.out.println("Please try again later or contact admin.");
                    return null; // exit if account is locked
                }
            } else {
                System.out.println("Login Successful!");
                return matchedUser; // return matched user
            }
        }

        return null; // return null if login fails
    }

    //method of userAction after login
    public static void userActions(User account) { //called directly without creating objects
        if (account == null) {
            System.out.println("Invalid user account!");
            return;
        }

        while (true) { // loop to show options
            System.out.println("\n1. Check Balance\n2. Withdraw Cash\n3. Deposit Cash\n4. Change PIN\n5. View Transaction History\n6. Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scan.nextLine()); // get choice as input

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 2:
                    withdrawCash(account); // withdraw cash
                    break;
                case 3:
                    depositCash(account); // deposit cash
                    break;
                case 4:
                    changePin(account); // change pin
                    break;
                case 5:
                    viewTransactionHistory(account); // view transaction history
                    break;
                case 6:
                    System.out.println("Logged out."); // log out
                    return;
                default:
                    System.out.println("Invalid choice! Try again."); // invalid input
            }
        }
    }

    //method to withdraw cash from user
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
        Transaction transaction = new Transaction(account.getUserId(), "Withdraw", amount);
        account.getTransactionHistory().add(transaction);

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



    private static void depositCash(User account) { //called directly without creating objects
        if (account == null) {
            System.out.println("Invalid user account!");
            return; // exit if invalid account
        }

        System.out.print("Enter amount to deposit: ");
        double amount = scan.nextDouble(); //get amount as input
        scan.nextLine();

        if (amount <= 0) {
            System.out.println("Invalid deposit amount!");
            return; // if amt invalid return
        }

        System.out.print("Enter number of 2000 notes: ");
        int atm2000Depo = scan.nextInt(); // get the no. of notes of 2000
        System.out.print("Enter number of 500 notes: ");
        int atm500Depo = scan.nextInt(); // get the no. of notes of 500
        System.out.print("Enter number of 200 notes: ");
        int atm200Depo = scan.nextInt(); // get the no. of notes of 200
        System.out.print("Enter number of 100 notes: ");
        int atm100Depo = scan.nextInt(); // get the no. of notes of 100
        scan.nextLine();

        //calculation of total deposit amount
        double totalDepositAmount = atm2000Depo * 2000 + atm500Depo * 500 + atm200Depo * 200 + atm100Depo * 100;

        if (totalDepositAmount != amount) {
            System.out.println("Total deposited amount does not match the expected deposit.");
            return;
        }

        // Update the user's balance and ATM balance
        account.deposit(amount);
        // store the transactions
        Transaction transaction = new Transaction(account.getUserId(), "Deposit", totalDepositAmount);
        account.getTransactionHistory().add(transaction);

        // notes count to deduct
        for (Notes note : ATM.getAtmNotes()) {
            if (note.getNote() == 2000)
                note.setCount(note.getCount() + atm2000Depo);
            else if (note.getNote() == 500)
                note.setCount(note.getCount() + atm500Depo);
            else if (note.getNote() == 200)
                note.setCount(note.getCount() + atm200Depo);
            else if (note.getNote() == 100)
                note.setCount(note.getCount() + atm100Depo);
        }

        System.out.println("Deposit successful! Denominations are:\n" +
                "2000 x " + atm2000Depo + "\n" +
                "500 x " + atm500Depo + "\n" +
                "200 x " + atm200Depo + "\n" +
                "100 x " + atm100Depo);
    }

    // method to change pin
    private static void changePin(User account) { //called directly without creating objects
        System.out.print("Enter current PIN: ");
        String currentPin = scan.nextLine(); // get the current pin

        if (account.getPin().equals(currentPin)) {
            System.out.print("Enter new PIN: ");
            String newPin = scan.nextLine(); // get the new pin
            account.setPin(newPin);
            System.out.println("PIN changed successfully!");
        }
        else {
            System.out.println("Incorrect current PIN!");
        }
    }

    private static void viewTransactionHistory(User account) { //called directly without creating objects
        System.out.println("Transaction History:");
        //to display user transaction history
        if (account.getTransactionHistory().isEmpty()) {
            System.out.println("No transactions found.");
        }
        else {
            for (Transaction transaction : account.getTransactionHistory()) {
                System.out.println("Performed By: " + transaction.getPerformedBy());
                System.out.println("Type: " + transaction.getType());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("---------------------------");
            }
        }
    }
}
