package org.example.view;

import org.example.models.Patient.PatientList;
import org.example.models.Staff.Doctor;
import org.example.models.Staff.DoctorList;
import org.example.models.Patient.Patient;
import org.example.utils.ValidateUserInput;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class for showing the doctor menu in the cli
 */
public class DoctorMenu {
    /**
     * Shows the main menu before access to functions
     * Asks for doctor id before granting access
     */
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter Doctor's ID: ");
            String id = scanner.nextLine().trim();

            Doctor doctor = DoctorList.getInstance().findByID(id);
            if (doctor == null) {
                System.out.println("Error: No doctor found with ID: " + id);
                return;
            }

            boolean run = true;
            while (run) {
                try {
                    System.out.println("\nDoctor Menu: " + doctor.getName());
                    System.out.println("1. View Assigned Patients");
                    System.out.println("2. Admit a Patient");
                    System.out.println("3. Discharge a Patient");
                    System.out.println("4. Exit");

                    int action = ValidateUserInput.getValidInteger(scanner, "Your Choice: ");

                    switch (action) {
                        case 1: //view the patients assigned to the doctor
                            doctor.viewAssignedPatients();
                            break;
                        case 2: // Admit a patient from the OPD status
                            System.out.print("Enter patient ID to admit (Enter 'q' to go back): ");
                            String IPDPatientID = scanner.nextLine().trim();
                            if (IPDPatientID.equalsIgnoreCase("q")) break;

                            Patient patient = PatientList.getInstance().findById(IPDPatientID);
                            if (patient != null) {
                                doctor.admitPatient(patient);
                            } else {
                                System.out.println("Error: No Patient Found with ID: " + IPDPatientID);
                            }
                            break;
                        case 3: //Discharge a patient
                            System.out.print("Enter patient ID to discharge (Enter 'q' to go back): ");
                            String dischargePatientID = scanner.nextLine().trim();
                            if (dischargePatientID.equalsIgnoreCase("q")) break;

                            Patient patientToDischarge = PatientList.getInstance().findById(dischargePatientID);
                            if (patientToDischarge != null) {
                                doctor.dischargePatients(patientToDischarge);
                            } else {
                                System.out.println("Error: No Patient Found with ID: " + dischargePatientID);
                            }
                            break;
                        case 4: // Exit menu
                            run = false;
                            System.out.println("Exiting Doctor Menu...");
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
        } catch (Exception e) {
            System.out.println("Critical Error: " + e.getMessage());
        }
    }
}
