package org.example.view;

import org.example.models.Staff.Receptionist;
import org.example.models.Staff.ReceptionistList;
import org.example.models.Patient.Patient;
import org.example.models.Staff.Doctor;
import org.example.models.Staff.DoctorList;
import org.example.utils.ValidateUserInput;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ReceptionistMenu {
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter Receptionist ID: ");
            String id = scanner.next().trim();

            Receptionist receptionist = ReceptionistList.getInstance().findById(id);
            if (receptionist == null) {
                System.out.println("Error: Receptionist not found.");
                return;
            }

            boolean run = true;
            while (run) {
                System.out.println("\n==============================");
                System.out.println("Receptionist Menu: " + receptionist.getName());
                System.out.println("==============================");
                System.out.println("1. Register and Assign a Patient to a Doctor");
                System.out.println("2. Exit");

                int action = ValidateUserInput.getValidInteger(scanner, "Your Choice: ");

                switch (action) {
                    case 1:
                        System.out.print("Enter patient name to register (Enter 'q' to go back): ");
                        scanner.nextLine(); // Consume leftover newline
                        String patientName = scanner.nextLine().trim();

                        if (patientName.equalsIgnoreCase("q")) break;
                        if (patientName.isEmpty()) {
                            System.out.println("Error: Patient name cannot be empty.");
                            break;
                        }

                        int patientAge;
                        try {
                            patientAge = ValidateUserInput.getValidInteger(scanner, "Enter patient age: ");
                            if (patientAge <= 0) {
                                System.out.println("Error: Age must be a positive number.");
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Invalid input. Please enter a valid age.");
                            scanner.nextLine(); // Clear invalid input
                            break;
                        }

                        DoctorList doctorList = DoctorList.getInstance();
                        List<Doctor> doctors = doctorList.getDoctors();

                        if (doctors.isEmpty()) {
                            System.out.println("No doctors available to assign.");
                            break;
                        }

                        System.out.println("\nAvailable Doctors:");
                        for (Doctor doctor : doctors) {
                            System.out.println("ID: " + doctor.getId() + " | Name: " + doctor.getName() + " | Specialty: " + doctor.getSpeciality());
                        }

                        System.out.print("Enter Doctor ID to assign: ");
                        id = scanner.next().trim();
                        Doctor selectedDoctor = doctorList.findByID(id);

                        if (selectedDoctor == null) {
                            System.out.println("Error: No doctor with the given ID found.");
                            break;
                        }

                        Patient newPatient = new Patient(patientName, patientAge);
                        receptionist.registerPatient(newPatient);
                        receptionist.assignPatientToDoctor(newPatient, selectedDoctor);

                        System.out.println("Patient " + patientName + " successfully assigned to Dr. " + selectedDoctor.getName());
                        break;
                    case 2:
                        run = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
