import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Adminlogin {
    static Scanner scan = new Scanner(System.in);

    static void adminLogin() {
        Admin admin = new Admin();

        for (int attempts = 1; attempts <= 3; attempts++) {
            System.out.print("Enter admin ID: ");
            String id = scan.nextLine();

            System.out.print("Enter admin password: ");
            String password = scan.nextLine();

            if (id.equals(admin.getId()) && password.equals(admin.getPassword())) {
                System.out.println("Admin Login Successful!");
                adminAction();
                return;
            } else {
                System.out.println("Invalid Admin ID or Admin PIN!");
                if (attempts == 3) {
                    System.out.println("Maximum attempts reached. Access denied.");
                    return;
                }
            }
        }
    }

    static void adminAction() {
        while (true) {
            System.out.println("\n1. Add User\n2. Delete User\n3. Deposit to ATM\n4. View All Users\n5. View All Transactions\n6. Check ATM Balance\n7. Logout");
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    depositToATM();
                    break;
                case 4:
                    viewAllUsers();
                    break;
                case 5:
                    viewAllTransactions();
                    break;
                case 6:
                    checkATMBalance();
                    break;
                case 7:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void addUser() {
        System.out.print("Enter new user ID: ");
        String userId = scan.nextLine();

        System.out.print("Enter initial PIN: ");
        String pin = scan.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = scan.nextDouble();
        scan.nextLine();

        ATM.userAccounts.add(new User(userId, pin, balance));
        System.out.println("User added successfully!");
    }

    static void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        String userId = scan.nextLine();

        for (User account : ATM.userAccounts) {
            if (account.getUserId().equals(userId)) {
                ATM.userAccounts.remove(account);
                System.out.println("User deleted successfully!");
                return;
            }
        }
        System.out.println("User not found!");
    }

    static void depositToATM() {
        System.out.print("Enter number of 2000 notes to deposit: ");
        int atm2000 = scan.nextInt();

        System.out.print("Enter number of 500 notes to deposit: ");
        int atm500 = scan.nextInt();

        System.out.print("Enter number of 200 notes to deposit: ");
        int atm200 = scan.nextInt();

        System.out.print("Enter number of 100 notes to deposit: ");
        int atm100 = scan.nextInt();

        int totalDepositAmount = atm100 * 100 + atm200 * 200 + atm500 * 500 + atm2000 * 2000;

        if (totalDepositAmount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        for (Notes note : ATM.getAtmNotes()) {
            if (note.getNote() == 2000) {
                note.setCount(note.getCount() + atm2000);
            } else if (note.getNote() == 500) {
                note.setCount(note.getCount() + atm500);
            } else if (note.getNote() == 200) {
                note.setCount(note.getCount() + atm200);
            } else if (note.getNote() == 100) {
                note.setCount(note.getCount() + atm100);
            }
        }

        ATM.setAtmBalance(ATM.getAtmBalance() + totalDepositAmount);
        Admin.allTransactionHistory.add(new Transaction("Admin", "Deposit to ATM", totalDepositAmount));

        System.out.println("Deposit successful!");
        System.out.println("Updated ATM Balance:" + Userlogin.atmBalance);
        System.out.println("Updated ATM Balance: " + ATM.getAtmBalance());
    }

    static void viewAllUsers() {
        for (User account : ATM.userAccounts) {
            System.out.println("User ID: " + account.getUserId());
        }
    }

    static void viewAllTransactions() {
        for (Transaction transaction : Admin.allTransactionHistory) {
            System.out.println("Performed by: " + transaction.getPerformedBy());
            System.out.println("Type: " + transaction.getType());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("---------------------------");
        }
    }

    static void checkATMBalance() {
        System.out.println("ATM Balance: " + ATM.getAtmBalance());
    }
}
