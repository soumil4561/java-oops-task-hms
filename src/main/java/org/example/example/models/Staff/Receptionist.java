package org.example.models.Staff;

import org.example.interfaces.ReceptionistActions;
import org.example.models.Patient.Patient;
import org.example.models.Patient.PatientList;

/**
 * Receptionist class for defining properties and actions of a receptionist.
 * A receptionist is responsible for registering patients and assigning them to doctors.
 * It extends the Staff class and implements the ReceptionistActions interface.
 */
public class Receptionist extends Staff implements ReceptionistActions {
    // Counter to generate unique IDs for each receptionist
    public static int receptionistCounter = 1;

    /**
     * Constructor to initialize a receptionist with a unique ID and name.
     *
     * @param name Name of the receptionist.
     */
    public Receptionist(String name) {
        super("R" + receptionistCounter, name, "Receptionist");
        receptionistCounter++;
    }

    /**
     * Registers a patient in the hospital system.
     *
     * @param patient The patient to be registered.
     * @throws IllegalArgumentException if the patient object is null.
     */
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

    /**
     * Assigns a patient to a doctor.
     *
     * @param patient The patient to be assigned.
     * @param doctor  The doctor to whom the patient will be assigned.
     * @throws IllegalArgumentException if either the patient or doctor is null.
     */
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

    /**
     * Defines the general duty performed by a receptionist.
     * This method prints a message indicating that the receptionist is scheduling appointments.
     */
    @Override
    public void performDuties() {
        System.out.println(getName() + " is scheduling appointments.");
    }
}
