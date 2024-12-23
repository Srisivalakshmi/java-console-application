import java.util.Scanner;

public class Userlogin {
    static void userLogin() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter user ID: ");
        String userId = scan.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scan.nextLine();

        for (UserAccount account : ATM.userAccounts) {
            if (account.getUserId().equals(userId) && account.getPin().equals(pin)) {
                System.out.println("Login Successful!");
                UserAction.userActions(account);
                return;
            }
        }

        System.out.println("Invalid user ID or PIN!");
    }
}
