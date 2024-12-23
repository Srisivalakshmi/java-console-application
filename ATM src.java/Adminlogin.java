import java.util.Scanner;
public class Adminlogin {
    static void adminLogin() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter admin password: ");
        String password = scan.nextLine();

        if ("admin123".equals(password)) {
            System.out.println("Admin Login Successful!");
            AdminAction.adminActions();
        } else {
            System.out.println("Invalid password!");
        }
    }
}