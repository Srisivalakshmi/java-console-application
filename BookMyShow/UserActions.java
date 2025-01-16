package BMS;

import java.util.Scanner;

class UserActions {

    public static User userLogin(Scanner scanner, String username) {
        for (User user : BMS.getUserList()) {
            if (user.getName().equals(username)) {
                System.out.print("Enter Password: ");
                int password = Integer.parseInt(scanner.nextLine());

                if (user.getPassword() == password) {
                    System.out.println("Login successful.");
                    return user;
                } else {
                    System.out.println("Incorrect password.");
                }
            }
        }
        System.out.println("User not found.");
        return null;
    }

    public static void register(Scanner scanner) {
        System.out.print("Enter New Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        int password = Integer.parseInt(scanner.nextLine());

        BMS.getUserList().add(new User(username, password));
        System.out.println("User registered successfully.");
    }
}

