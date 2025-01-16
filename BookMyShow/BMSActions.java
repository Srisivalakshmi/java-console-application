package BMS;

import java.util.Scanner;

public class BMSActions {

    public static void start(){
        Scanner scanner = new Scanner(System.in);
        AdminActions adminActions = new AdminActions();

        // Initialize with default admin and user
        BMS.getAdminList().add(new Admin("admin", 1234));
        BMS.getUserList().add(new User("user", 5678));

        while (true) {
            System.out.println("\nBOOK MY SHOW");
            System.out.println("1. Admin\n2. User\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    Admin admin = AdminActions.adminLogin(scanner);
                    if (admin != null) {
                        adminMenu(adminActions);
                    } else {
                        System.out.println("Invalid admin credentials.");
                    }
                    break;

                case 2:
                    handleUserActions(scanner);
                    break;

                case 3:
                    System.out.println("Exiting Book My Show.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminMenu(AdminActions adminActions) {
        Scanner scanner = new Scanner(System.in);
        boolean exitAdmin = false;

        while (!exitAdmin) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Theatre\n2. View Theatres\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    adminActions.addTheatre(scanner);
                    break;
                case 2:
                    adminActions.viewTheatres();
                    break;
                case 3:
                    System.out.println("Exiting Book My Show.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleUserActions(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User user = UserActions.userLogin(scanner, username);

        if (user == null) {
            System.out.println("No user found. Would you like to sign up? (1 for Yes, 0 for No): ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                UserActions.register(scanner);
            }
        } else {
            System.out.println("Welcome " + user.getName() + "!");
        }
    }
}
