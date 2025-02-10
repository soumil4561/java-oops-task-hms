package org.example.models.Staff;

import org.example.interfaces.DoctorActions;
import org.example.models.Patient.Patient;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Staff implements DoctorActions {
    private static int doctorCounter = 1;
    private final List<Patient> assignedPatients;
    private final String speciality;

    public Doctor(String name, String speciality) {
        super("D" + doctorCounter, name, "Doctor");
        doctorCounter++;
        assignedPatients = new ArrayList<>();
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public List<Patient> getAssignedPatients() {
        return assignedPatients;
    }

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

    @Override
    public void performDuties() {
        System.out.println("Doctor is seeing the patient.");
    }

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
            System.out.println("Patient " + patient.getName() + " has been admitted by "+this.getName());

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while admitting patient: " + e.getMessage());
        }
    }

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
            System.out.println("Patient " + patient.getName() + " has been discharged by "+this.getName());

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while discharging patient: " + e.getMessage());
        }
    }

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
