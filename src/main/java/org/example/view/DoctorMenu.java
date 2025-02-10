package org.example.view;

import org.example.models.Patient.PatientList;
import org.example.models.Staff.Doctor;
import org.example.models.Staff.DoctorList;
import org.example.models.Patient.Patient;
import org.example.utils.ValidateUserInput;

import java.util.Scanner;

public class DoctorMenu {
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Doctor's ID: ");

        String id = scanner.nextLine();
        Doctor doctor = DoctorList.getInstance().findByID(id);
        boolean run = true;
        while (run) {
            System.out.println("\nDoctor Menu: " + doctor.getName());
            System.out.println("1. View Assigned Patients");
            System.out.println("2. Admit a Patient");
            System.out.println("3. Discharge a Patient");
            System.out.println("4. Exit");
            int action = ValidateUserInput.getValidInteger(scanner, "Your Choice: ");

            switch (action) {
                case 1:
                    doctor.viewAssignedPatients();
                    break;
                case 2:
                    System.out.println("Enter patient ID to admit (Enter q to go back): ");
                    String IPDPatientID = scanner.next();
                    if(IPDPatientID.equals("q")) break;
                    Patient patient = PatientList.getInstance().findById(IPDPatientID);
                    if(patient!=null) doctor.admitPatient(patient);
                    else System.out.println("No Patient Found");
                    break;
                case 3:
                    System.out.println("Enter patient ID to discharge (Enter q to go back):");
                    String dischargePatientID = scanner.next();
                    if(dischargePatientID.equals("q")) break;
                    Patient patient1 = PatientList.getInstance().findById(dischargePatientID);
                    if(patient1!=null) doctor.dischargePatients(patient1);
                    else System.out.println("No Patient found");
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
