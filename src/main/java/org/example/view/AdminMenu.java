package org.example.view;

import org.example.models.Hospital.Hospital;
import org.example.models.Staff.Admin;
import org.example.utils.ConfigLoader;

import java.util.Scanner;

public class AdminMenu {
    public static void showMenu(Hospital hospital) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Admin Password:");
        String enteredPassword = scanner.nextLine();

        if (enteredPassword.equals(ConfigLoader.getAdminPassword())) {
            System.out.println("Access Granted!");
            Admin admin = Admin.getInstance(hospital);
            displayAdminOptions(admin);
        } else {
            System.out.println("Access Denied!");
        }
    }

    private static void displayAdminOptions(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Change Hospital Name");
            System.out.println("2. Change Hospital Address");
            System.out.println("3. Add Doctor");
            System.out.println("4. Add Receptionist");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new hospital name: ");
                    String newName = scanner.nextLine();
                    admin.setHospitalName(newName);
                    break;
                case 2:
                    System.out.print("Enter new hospital address: ");
                    String newAddress = scanner.nextLine();
                    admin.setHospitalAddress(newAddress);
                    break;
                case 3:
                    System.out.print("Enter doctor name: ");
                    String doctorName = scanner.nextLine();
                    System.out.print("Enter doctor's specialty: ");
                    String doctorSpecialty = scanner.nextLine();
                    admin.addDoctor(doctorName, doctorSpecialty);
                    break;
                case 4:
                    System.out.print("Enter receptionist name: ");
                    String receptionistName = scanner.nextLine();
                    admin.addReceptionist(receptionistName);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
