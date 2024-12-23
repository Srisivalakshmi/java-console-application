import java.util.Scanner;

public class UserAction {
    static void userActions(UserAccount account) {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("1. Check Balance\n2. Withdraw Cash\n3. Deposit Cash\n4. Change PIN\n5. View Transactions\n6. Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: " + account.getBalance());
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
                    AdminAction.viewAllTransactions();
                    break;
                case 6:
                    System.out.println("Logged out");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void withdrawCash(UserAccount account) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: ");
        double amount = scan.nextDouble();
        scan.nextLine();

        if (amount <= account.getBalance() && amount % 100 == 0) {
            account.withdraw(amount);
            ATM.transactionHistory.add("Withdrawn: " + amount);
            System.out.println("Withdrawal successful! Remaining balance: " + account.getBalance());
        }
        else {
            System.out.println("Invalid amount or insufficient balance!");
        }
    }

    static void depositCash(UserAccount account) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter amount to deposit: ");
        double amount = scan.nextDouble();
        scan.nextLine();

        account.deposit(amount);
        ATM.transactionHistory.add("Deposited: " + amount);
        System.out.println("Deposit successful! New balance: " + account.getBalance());
    }

    static void changePin(UserAccount account) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter current PIN: ");
        String currentPin = scan.nextLine();

        if (account.getPin().equals(currentPin)) {
            System.out.print("Enter new PIN: ");
            String newPin = scan.nextLine();
            account.setPin(newPin);
            ATM.transactionHistory.add("PIN changed successfully.");
            System.out.println("PIN changed successfully!");
        }
        else {
            System.out.println("Incorrect current PIN!");
        }
    }
}


