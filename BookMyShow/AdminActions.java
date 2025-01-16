package BMS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdminActions {
    public static Admin adminLogin(Scanner scanner) {
        System.out.print("Enter Admin Name: ");
        String adminName = scanner.nextLine();

        int attempts = 0;
        while (attempts < 3) {
            for (Admin admin : BMS.getAdminList()) {
                if (admin.getName().equals(adminName)) {
                    System.out.print("Enter Password: ");
                    int password = Integer.parseInt(scanner.nextLine());

                    if (admin.getName().equals(adminName) && admin.getPassword() == password) {
                        System.out.println("Login successful.");
                        return admin;
                    } else {
                        System.out.println("Incorrect admin name or password!");
                    }
                }
            }
            attempts++;
            System.out.println("Invalid admin name. Remaining attempts: " + (3 - attempts));
        }
        System.out.println("Login failed! So many attempts failed!");
        return null;
    }

    public void addTheatre(Scanner scanner) {
        System.out.print("Enter Theatre Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        if (BMS.getTheatreMap().containsKey(name) && BMS.getTheatreMap().get(name).getLocation().equals(location)) {
            System.out.println("Theatre already exists.");
            return;
        }

        System.out.print("Enter Number of Screens: ");
        int screenCount = Integer.parseInt(scanner.nextLine());
        HashMap<String, Screen> screens = new HashMap<>();

        for (int i = 0; i < screenCount; i++) {
            System.out.print("Enter Screen Name: ");
            String screenName = scanner.nextLine();
            System.out.print("Enter Number of Seats: ");
            int seats = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Grid Pattern (e.g., 2*4*2): ");
            String gridPattern = scanner.nextLine();

            HashMap<Character, ArrayList<String>> seatingArrangement = Utility.generateSeatingPatterns(seats, gridPattern);
            if (seatingArrangement == null) {
                System.out.println("Skipping screen due to invalid configuration.");
                continue;
            }

            screens.put(screenName, new Screen(screenName, seats, seatingArrangement));
        }

        Theatre theatre = new Theatre(name, screens, location);
        BMS.getTheatreMap().put(name, theatre);
        System.out.println("Theatre added successfully.");
    }

    public void viewTheatres() {
        for (var entry : BMS.getTheatreMap().entrySet()) {
            Theatre theatre = entry.getValue();
            System.out.println("Theatre Name: " + theatre.getName());
            System.out.println("Location: " + theatre.getLocation());
            System.out.println("Screens:");
            System.out.println("------------------");

            for (var screenEntry : theatre.getScreens().entrySet()) {
                Screen screen = screenEntry.getValue();
                System.out.println("  Screen Name: " + screen.getName());
                System.out.println("  Number of Seats: " + screen.getSeats());
                System.out.println("  Seating Arrangement: " + screen.getSeatingArrangement());
            }
        }
    }
}
