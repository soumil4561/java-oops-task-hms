package org.example.view;

import org.example.models.Staff.Doctor;
import org.example.models.Staff.DoctorList;
import org.example.models.Patient.Patient;

import java.util.List;
import java.util.Scanner;

public class DoctorMenu {
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        DoctorList doctorList = DoctorList.getInstance();
        List<Doctor> doctors = doctorList.getDoctors();

        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        System.out.println("Select a doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i).getName());
        }

        int choice = scanner.nextInt();
        if (choice < 1 || choice > doctors.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Doctor doctor = doctors.get(choice - 1);
        boolean run = true;
        while (run) {
            System.out.println("\nDoctor Menu: " + doctor.getName());
            System.out.println("1. View Assigned Patients");
            System.out.println("2. Admit a Patient");
            System.out.println("3. Discharge a Patient");
            System.out.println("4. Exit");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    doctor.viewAssignedPatients();
                    break;
                case 2:
                    System.out.println("Enter patient name to admit:");
                    String admitName = scanner.next();
                    //ok will do it
                    break;
                case 3:
                    System.out.println("Enter patient name to discharge:");
                    String dischargeName = scanner.next();
//                    doctor.dischargePatients(new Patient(dischargeName, 30, doctor));
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
