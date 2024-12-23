import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<UserAccount> userAccounts = new ArrayList<>();
    static ArrayList<String> transactionHistory = new ArrayList<>();
    static int atm2000 = 50, atm500 = 100, atm200 = 200, atm100 = 500; 
    public static void login(){
        while (true) {
            System.out.println("1. Admin Login\n2. User Login\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scan.next());

            switch (choice) {
                case 1:
                    Adminlogin.adminLogin();
                    break;
                case 2:
                    Userlogin.userLogin();
                    break;
                case 3:
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
