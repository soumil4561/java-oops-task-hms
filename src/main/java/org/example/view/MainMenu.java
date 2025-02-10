package org.example.view;

import org.example.models.Hospital.Hospital;
import org.example.models.Staff.DoctorList;
import org.example.models.Staff.ReceptionistList;
import org.example.utils.ValidateUserInput;

import java.util.Scanner;

public class MainMenu {
    private final Hospital hospital;

    public MainMenu(Hospital hospital) {
        this.hospital = hospital;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\n\nWelcome to " + hospital.getName() + "\nAddress: " + hospital.getAddress());
            System.out.println("\nSelect option:\n1. Receptionist Menu\n2. Doctor Menu\n3. Admin Dashboard\n4. Show Staff List\n5. Quit");
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
                    ReceptionistList.getInstance().viewStaffList();
                    DoctorList.getInstance().viewStaffList();
                    break;
                case 5:
                    System.out.println("Quitting....");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}
