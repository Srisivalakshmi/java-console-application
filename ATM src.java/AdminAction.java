import java.util.Scanner;

public class AdminAction {
    static void adminActions() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("1. Add User\n2. Delete User\n3. Deposit to ATM\n4. View Transactions\n5. Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scan.next());

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
                    viewAllTransactions();
                    break;
                case 5:
                    System.out.println("Logged out");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void addUser() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter new user ID: ");
        String userId = scan.nextLine();
        System.out.print("Enter initial PIN: ");
        String pin = scan.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scan.nextDouble();
        ATM.scan.nextLine();

        ATM.userAccounts.add(new UserAccount(userId, pin, balance));
        System.out.println("User added successfully!");
    }

    static void deleteUser() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter user ID to delete: ");
        String userId = scan.nextLine();

        for (UserAccount account : ATM.userAccounts) {
            if (account.getUserId().equals(userId)) {
                ATM.userAccounts.remove(account);
                System.out.println("User deleted successfully!");
                return;
            }
        }

        System.out.println("User not found!");
    }

    static void depositToATM() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of 2000 notes to deposit: ");
        ATM.atm2000 += scan.nextInt();
        System.out.print("Enter number of 500 notes to deposit: ");
        ATM.atm500 += scan.nextInt();
        System.out.print("Enter number of 200 notes to deposit: ");
        ATM.atm200 += scan.nextInt();
        System.out.print("Enter number of 100 notes to deposit: ");
        ATM.atm100 += scan.nextInt();
        ATM.scan.next();

        System.out.println("Cash deposited to ATM successfully!");
    }

    static void viewAllTransactions() {
        System.out.println("Transaction History:");
        for (String transaction : ATM.transactionHistory) {
            System.out.println(transaction);
        }
    }
}
