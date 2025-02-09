package org.example.view;

import org.example.models.Staff.Receptionist;
import org.example.models.Staff.ReceptionistList;
import org.example.models.Patient.Patient;
import org.example.models.Staff.Doctor;
import org.example.models.Staff.DoctorList;

import java.util.List;
import java.util.Scanner;

public class ReceptionistMenu {
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        ReceptionistList receptionistList = ReceptionistList.getInstance();
        List<Receptionist> receptionists = receptionistList.getReceptionists();

        if (receptionists.isEmpty()) {
            System.out.println("No receptionists available.");
            return;
        }

        System.out.println("Select a receptionist:");
        for (int i = 0; i < receptionists.size(); i++) {
            System.out.println((i + 1) + ". " + receptionists.get(i).getName());
        }

        int choice = scanner.nextInt();
        if (choice < 1 || choice > receptionists.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Receptionist receptionist = receptionists.get(choice - 1);
        boolean run = true;
        while (run) {
            System.out.println("\nReceptionist Menu: " + receptionist.getName());
            System.out.println("1. Register and Assign a Patient to a Doctor");
            System.out.println("2. Exit");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    System.out.println("Enter patient name to register:");
                    scanner.nextLine(); // Consume leftover newline
                    String patientName = scanner.nextLine();

                    System.out.println("Enter patient age:");
                    int patientAge = scanner.nextInt();


                    DoctorList doctorList = DoctorList.getInstance();
                    List<Doctor> doctors = doctorList.getDoctors();
                    if (doctors.isEmpty()) {
                        System.out.println("No doctors available to assign.");
                        break;
                    }

                    System.out.println("Select a doctor:");
                    for (int i = 0; i < doctors.size(); i++) {
                        System.out.println((i + 1) + ". " + doctors.get(i).getName());
                    }

                    int doctorChoice = scanner.nextInt();
                    if (doctorChoice < 1 || doctorChoice > doctors.size()) {
                        System.out.println("Invalid doctor choice.");
                        break;
                    }

                    Doctor selectedDoctor = doctors.get(doctorChoice - 1);
                    Patient newPatient = new Patient(patientName, patientAge);
                    receptionist.registerPatient(newPatient);
                    receptionist.assignPatientToDoctor(newPatient, selectedDoctor);
                    break;
                case 2:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
