import java.util.Scanner;

public class Userlogin {
    public static double atmBalance;
    static Scanner scan = new Scanner(System.in);

    public static void userLogin() {
        int attempts = 0;

        while (attempts < 3) {
            System.out.print("Enter user ID: ");
            String userId = scan.nextLine();

            User matchedUser = null;

            for (User account : ATM.getUserAccounts()) {
                if (account.getUserId().equals(userId)) {
                    matchedUser = account;
                    break;
                }
            }

            if (matchedUser == null) {
                System.out.println("Invalid User ID! Please try again.");
                attempts++;
                continue;
            }

            if (matchedUser.isLocked()) {
                System.out.println("This account is locked. Please contact admin.");
                return;
            }

            System.out.print("Enter PIN: ");
            String pin = scan.nextLine();

            if (!matchedUser.getPin().equals(pin)) {
                System.out.println("Invalid PIN! Please try again.");
                attempts++;
            } else {
                System.out.println("Login Successful!");
                userActions(matchedUser);
                return;
            }

            if (attempts >= 3) {
                matchedUser.lockAccount();
                System.out.println("Your account has been locked due to too many failed attempts.");
                System.out.println("Please try again later or contact admin.");
                return;
            }
        }
    }

    public static void userActions(User account) {
        while (true) {
            System.out.println("\n1. Check Balance\n2. Withdraw Cash\n3. Deposit Cash\n4. Change PIN\n5. View Transaction History\n6. Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: " + User.getBalance());
                    break;
                case 2:
                    withdrawCash(account);
                    break;
                case 3:
                    depositCash(account);
                    break;
                case 4:
                    changePin(account);
                    break;
                case 5:
                    viewTransactionHistory(account);
                    break;
                case 6:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    public static void withdrawCash(User user) {
        System.out.print("Enter amount to withdraw: ");
        int amount = scan.nextInt();
        scan.nextLine();

        if (amount % 100 != 0) {
            System.out.println("Withdrawal amount must be in multiples of 100!");
            return;
        }

        if (amount > user.getBalance()) {
            System.out.println("Insufficient account balance!");
            return;
        }

        if (amount > ATM.getAtmBalance()) {
            System.out.println("ATM does not have sufficient funds!");
            return;
        }

        int remainingAmount = amount;
        int notes2000 = 0, notes500 = 0, notes200 = 0, notes100 = 0;

        for (Notes note : ATM.getAtmNotes()) {
            int noteValue = note.getNote();
            int availableNotes = note.getCount();

            if (availableNotes > 0) {
                int neededNotes = remainingAmount / noteValue;
                int notestoDeduct = Math.min(neededNotes, availableNotes);

                remainingAmount -= notestoDeduct * noteValue;

                if (noteValue == 2000)
                    notes2000 = notestoDeduct;
                else if (noteValue == 500)
                    notes500 = notestoDeduct;
                else if (noteValue == 200)
                    notes200 = notestoDeduct;
                else if (noteValue == 100)
                    notes100 = notestoDeduct;

                note.setCount(availableNotes - notestoDeduct);
            }
        }

        if (remainingAmount < 0) {
            System.out.println("ATM does not have enough denominations to fulfill this request.");
            return;
        }

        user.withdraw(amount);
        ATM.setAtmBalance(ATM.getAtmBalance() - amount);

        Transaction transaction = new Transaction(user.getUserId(), "Withdraw", amount);
        user.getTransactionHistory().add(transaction);
        Admin.allTransactionHistory.add(transaction);


        double atmBalance = ATM.getAtmBalance() - amount;
        System.out.println("Withdrawal successful!");

    }

    private static void depositCash(User user) {
        System.out.print("Enter amount to deposit: ");
        double amount = scan.nextDouble();
        scan.nextLine();

        if (amount <= 0) {
            System.out.println("Invalid deposit amount!");
            return;
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

        double totalDepositAmount = atm2000Depo * 2000 + atm500Depo * 500 + atm200Depo * 200 + atm100Depo * 100;

        if (totalDepositAmount != amount) {
            System.out.println("Total deposited amount does not match the expected deposit.");
            return;
        }

        user.deposit(amount);
        ATM.processTransaction(user.getUserId(), "Deposit", amount);
        //User.getTransactionHistory().add(new Transaction(user.getUserId(), "Deposit", amount));

        Transaction transaction = new Transaction(user.getUserId(), "Deposit:", totalDepositAmount);
        user.addTransaction(transaction);

        for (Notes note : ATM.getAtmNotes()) {
            if (note.getNote() == 2000) note.setCount(note.getCount() + atm2000Depo);
            else if (note.getNote() == 500) note.setCount(note.getCount() + atm500Depo);
            else if (note.getNote() == 200) note.setCount(note.getCount() + atm200Depo);
            else if (note.getNote() == 100) note.setCount(note.getCount() + atm100Depo);
        }

        System.out.println("Deposit successful!");
    }


    private static void changePin(User account) {
        System.out.print("Enter current PIN: ");
        String currentPin = scan.nextLine();

        if (account.getPin().equals(currentPin)) {
            System.out.print("Enter new PIN: ");
            String newPin = scan.nextLine();
            account.setPin(newPin);
            System.out.println("PIN changed successfully!");
        } else {
            System.out.println("Incorrect current PIN!");
        }
    }

    private static void viewTransactionHistory(User account) {
        for (User user : ATM.userAccounts) {
            System.out.println("Transaction History:" +user.getUserId());
            if (User.getTransactionHistory().isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                for (Transaction transaction : User.getTransactionHistory()) {
                    System.out.println("Performed By: " + transaction.getPerformedBy());
                    System.out.println("Type: " + transaction.getType());
                    System.out.println("Amount: " + transaction.getAmount());
                    System.out.println("---------------------------");
                }
            }
        }
    }
}
