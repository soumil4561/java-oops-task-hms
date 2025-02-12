package org.example.view;

import org.example.models.Hospital.Hospital;
import org.example.models.Staff.Admin;
import org.example.utils.ConfigLoader;
import org.example.utils.ValidateUserInput;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class for the admin menu display in the cli
 */
public class AdminMenu {
    /**
     * Shows the main menu before accessing the options.
     * Ask for the admin password before access can be granted
     * @param hospital Hospital instance handled by the admin
     */
    public static void showMenu(Hospital hospital) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter Admin Password: ");
            String enteredPassword = scanner.nextLine().trim();

            if (enteredPassword.equals(ConfigLoader.getAdminPassword())) {
                System.out.println("Access Granted!");
                Admin admin = Admin.getInstance(hospital);
                displayAdminOptions(admin, scanner);
            } else {
                System.out.println("Access Denied!");
            }
        } catch (Exception e) {
            System.out.println("Error: An unexpected issue occurred. " + e.getMessage());
        }
    }

    /**
     * Display the admin functions on screen once the access is granted.
     * Takes in user input accordingly
     * @param admin: The admin object which will implement the method
     * @param scanner Scanner object for taking user input
     */
    private static void displayAdminOptions(Admin admin, Scanner scanner) {
        boolean running = true;

        while (running) {
            try {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. Change Hospital Name");
                System.out.println("2. Change Hospital Address");
                System.out.println("3. Add Doctor");
                System.out.println("4. Add Receptionist");
                System.out.println("5. Exit");

                int choice = ValidateUserInput.getValidInteger(scanner, "Your Choice: ");

                switch (choice) {
                    case 1: // Change hospital name
                        System.out.print("Enter new hospital name (Enter 'q' to quit): ");
                        String newName = scanner.nextLine().trim();
                        if (newName.equalsIgnoreCase("q")) break;
                        admin.setHospitalName(newName);
                        break;
                    case 2: //Change Hospital Address
                        System.out.print("Enter new hospital address (Enter 'q' to quit): ");
                        String newAddress = scanner.nextLine().trim();
                        if (newAddress.equalsIgnoreCase("q")) break;
                        admin.setHospitalAddress(newAddress);
                        break;
                    case 3: //Register a new Doctor
                        System.out.print("Enter doctor name (Enter 'q' to quit): ");
                        String doctorName = scanner.nextLine().trim();
                        if (doctorName.equalsIgnoreCase("q")) break;
                        System.out.print("Enter doctor's specialty (Enter 'q' to quit): ");
                        String doctorSpecialty = scanner.nextLine().trim();
                        if (doctorSpecialty.equalsIgnoreCase("q")) break;
                        admin.addDoctor(doctorName, doctorSpecialty);
                        break;
                    case 4: //Register a new Receptionist
                        System.out.print("Enter receptionist name (Enter 'q' to quit): ");
                        String receptionistName = scanner.nextLine().trim();
                        if (receptionistName.equalsIgnoreCase("q")) break;
                        admin.addReceptionist(receptionistName);
                        break;
                    case 5: //Exit the menu
                        running = false;
                        System.out.println("Exiting Admin Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
