package org.example.models.Staff;

import org.example.interfaces.ReceptionistActions;
import org.example.models.Patient.Patient;
import org.example.models.Patient.PatientList;

public class Receptionist extends Staff implements ReceptionistActions {
    public static int receptionistCounter = 1;

    public Receptionist(String name) {
        super("R" + receptionistCounter, name, "Receptionist");
        receptionistCounter++;
    }

    @Override
    public void registerPatient(Patient patient) {
        try {
            if (patient == null) {
                throw new IllegalArgumentException("Patient cannot be null.");
            }
            patient.addPatient();
            System.out.println("Patient " + patient.getName() + " registered successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while registering patient: " + e.getMessage());
        }
    }

    @Override
    public void assignPatientToDoctor(Patient patient, Doctor doctor) {
        try {
            if (patient == null || doctor == null) {
                throw new IllegalArgumentException("Doctor / patient cannot be null.");
            }

            if (doctor.getAssignedPatients().contains(patient)) {
                System.out.println("Patient " + patient.getName() + " is already assigned to Doctor " + doctor.getName());
                return;
            }

            doctor.addPatientToList(patient);
            System.out.println("Receptionist " + getName() + " assigned patient " + patient.getName() + " to Doctor " + doctor.getName());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while assigning patient: " + e.getMessage());
        }
    }

    @Override
    public void performDuties() {
        System.out.println(getName() + " is scheduling appointments.");
    }
}
