package org.example.view;

import org.example.models.Hospital.Hospital;
import org.example.models.Staff.DoctorList;
import org.example.models.Staff.ReceptionistList;
import org.example.utils.ValidateUserInput;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    private final Hospital hospital;

    public MainMenu(Hospital hospital) {
        if (hospital == null) {
            throw new IllegalArgumentException("Error: Hospital instance cannot be null.");
        }
        this.hospital = hospital;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            try {
                System.out.println("\n\n==============================");
                System.out.println("Welcome to " + hospital.getName());
                System.out.println("Address: " + hospital.getAddress());
                System.out.println("==============================");
                System.out.println("Select an option:");
                System.out.println("1. Receptionist Menu");
                System.out.println("2. Doctor Menu");
                System.out.println("3. Admin Dashboard");
                System.out.println("4. Show Staff List");
                System.out.println("5. Quit");

                int choice = ValidateUserInput.getValidInteger(scanner, "Your Choice: ");

                switch (choice) {
                    case 1:
                        ReceptionistMenu.showMenu();
                        break;
                    case 2:
                        DoctorMenu.showMenu();
                        break;
                    case 3:
                        AdminMenu.showMenu(hospital);
                        break;
                    case 4:
                        System.out.println("\n---- Receptionists ----");
                        ReceptionistList.getInstance().viewStaffList();
                        System.out.println("\n---- Doctors ----");
                        DoctorList.getInstance().viewStaffList();
                        break;
                    case 5:
                        System.out.print("Are you sure you want to quit? (yes/no): ");
                        String confirmation = scanner.next().trim().toLowerCase();

                        if (confirmation.equals("yes") || confirmation.equals("y")) {
                            System.out.println("Quitting...");
                            run = false;
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
