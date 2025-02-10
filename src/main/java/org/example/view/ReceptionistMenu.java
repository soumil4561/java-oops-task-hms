package org.example.view;

import org.example.models.Staff.Receptionist;
import org.example.models.Staff.ReceptionistList;
import org.example.models.Patient.Patient;
import org.example.models.Staff.Doctor;
import org.example.models.Staff.DoctorList;
import org.example.utils.ValidateUserInput;

import java.util.List;
import java.util.Scanner;

public class ReceptionistMenu {
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Receptionist ID: ");

        String id = scanner.next();

        Receptionist receptionist = ReceptionistList.getInstance().findById(id);
        if(receptionist==null){
            System.out.println("Receptionist not found");
            return;
        }

        boolean run = true;
        while (run) {
            System.out.println("\nReceptionist Menu: " + receptionist.getName());
            System.out.println("1. Register and Assign a Patient to a Doctor");
            System.out.println("2. Exit");
            int action = ValidateUserInput.getValidInteger(scanner, "Your Choice: ");

            switch (action) {
                case 1:
                    System.out.println("Enter patient name to register (Enter q to go back):");
                    scanner.nextLine();

                    String patientName = scanner.nextLine();
                    if(patientName.equals("q")) break;

                    int patientAge = ValidateUserInput.getValidInteger(scanner, "Enter patient age: ");

                    DoctorList doctorList = DoctorList.getInstance();
                    List<Doctor> doctors = doctorList.getDoctors();
                    if (doctors.isEmpty()) {
                        System.out.println("No doctors available to assign.");
                        break;
                    }
                    System.out.println("Select a doctor (Enter ID):");
                    for (Doctor doctor: doctors) {
                        System.out.println(doctor.getId()+ " : " + doctor.getName()+" : "+doctor.getSpeciality());
                    }

                    id = scanner.next();
                    Doctor selectedDoctor = DoctorList.getInstance().findByID(id);
                    if(selectedDoctor==null){
                        System.out.println("No doctor with given ID found.");
                        break;
                    }
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
