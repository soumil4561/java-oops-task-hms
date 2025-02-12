package org.example.models.Staff;

import org.example.interfaces.DoctorActions;
import org.example.models.Patient.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Doctor class defining the properties and responsibilities of a doctor.
 * Each doctor has a unique ID, a name, a specialty, and a list of assigned patients.
 * Implements the DoctorActions interface to define doctor-specific actions.
 */
public class Doctor extends Staff implements DoctorActions {
    // Counter for generating unique doctor IDs
    private static int doctorCounter = 1;
    // List of patients assigned to the doctor
    private final List<Patient> assignedPatients;
    // Doctor's specialty
    private final String speciality;

    /**
     * Constructor to initialize a doctor with a unique ID, name, and specialty.
     * @param name Name of the doctor
     * @param speciality Specialty of the doctor
     */
    public Doctor(String name, String speciality) {
        super("D" + doctorCounter, name, "Doctor");
        doctorCounter++;
        assignedPatients = new ArrayList<>();
        this.speciality = speciality;
    }

    /**
     * Returns the specialty of the doctor.
     * @return Doctor's specialty
     */
    public String getSpeciality() {
        return speciality;
    }

    /**
     * Returns the list of patients assigned to the doctor.
     * @return List of assigned patients
     */
    public List<Patient> getAssignedPatients() {
        return assignedPatients;
    }

    /**
     * Assigns a patient to the doctor after validating the input.
     * @param patient Patient to be assigned
     * @throws IllegalArgumentException if the patient is null
     * @throws IllegalStateException if the patient is already assigned to the doctor
     */
    public void addPatientToList(Patient patient) {
        try {
            if (patient == null) {
                throw new IllegalArgumentException("Patient cannot be null.");
            }

            if (assignedPatients.contains(patient)) {
                throw new IllegalStateException("Patient " + patient.getName() + " is already assigned to this doctor.");
            }

            assignedPatients.add(patient);
            System.out.println("Patient " + patient.getName() + " assigned to Dr. " + getName());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while adding patient: " + e.getMessage());
        }
    }

    /**
     * Performs doctor-specific duties.
     */
    @Override
    public void performDuties() {
        System.out.println("Doctor is seeing the patient.");
    }

    /**
     * Admits a patient under the doctor's supervision after validation.
     * @param patient Patient to be admitted
     * @throws IllegalArgumentException if the patient is null
     * @throws IllegalStateException if the doctor is not assigned to the patient or the patient is already admitted
     */
    @Override
    public void admitPatient(Patient patient) {
        try {
            if (patient == null) {
                throw new IllegalArgumentException("Patient cannot be null.");
            }

            if (!assignedPatients.contains(patient)) {
                throw new IllegalStateException("Doctor " + getName() + " is not assigned to this patient.");
            }

            if ("IPD".equals(patient.getStatus())) {
                throw new IllegalStateException("Patient " + patient.getName() + " is already admitted.");
            }

            patient.markAsInPatient();
            System.out.println("Patient " + patient.getName() + " has been admitted by " + this.getName());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while admitting patient: " + e.getMessage());
        }
    }

    /**
     * Discharges a patient under the doctor's supervision after validation.
     * @param patient Patient to be discharged
     * @throws IllegalArgumentException if the patient is null
     * @throws IllegalStateException if the doctor is not assigned to the patient
     */
    @Override
    public void dischargePatients(Patient patient) {
        try {
            if (patient == null) {
                throw new IllegalArgumentException("Patient cannot be null.");
            }

            if (!assignedPatients.contains(patient)) {
                throw new IllegalStateException("Doctor " + getName() + " is not assigned to this patient.");
            }

            assignedPatients.remove(patient);
            patient.dischargePatient();
            System.out.println("Patient " + patient.getName() + " has been discharged by " + this.getName());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while discharging patient: " + e.getMessage());
        }
    }

    /**
     * Displays a formatted list of patients assigned to the doctor.
     */
    @Override
    public void viewAssignedPatients() {
        System.out.println("\nDoctor " + getName() + "'s Patients:");
        System.out.println("+----+------------+--------+");
        System.out.println("| ID | Name       | Status |");
        System.out.println("+----+------------+--------+");

        if (assignedPatients.isEmpty()) {
            System.out.println("| No assigned patients       |");
        } else {
            for (Patient p : assignedPatients) {
                System.out.printf("| %-10s | %-10s | %-6s |\n", p.getId(), p.getName(), p.getStatus());
            }
        }

        System.out.println("+----+------------+--------+");
    }
}
