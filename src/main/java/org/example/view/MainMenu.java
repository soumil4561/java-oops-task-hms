package org.example.view;

import org.example.models.Hospital.Hospital;

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
            System.out.println("Welcome to " + hospital.getName() + "\nAddress: " + hospital.getAddress());
            System.out.println("Who are you? (Enter number):\n1. Receptionist\n2. Doctor\n3. Admin\n4. Quit");
            int choice = scanner.nextInt();
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
