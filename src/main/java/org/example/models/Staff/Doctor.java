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
        super("D"+doctorCounter, name, "Doctor");
        doctorCounter++;
        assignedPatients = new ArrayList<>();
        this.speciality = speciality;
    }

    public void addPatienttoList(Patient patient){
        assignedPatients.add(patient);
    }

    @Override
    public void performDuties() {
        System.out.println("Doctor is seeing the patient.");
    }

    @Override
    public void admitPatient(Patient patient) {
        if(assignedPatients.contains(patient)){
            patient.markAsInPatient();
        }
        else{
            System.out.println("Doctor " + getName() + " is not assigned to this patient.");
        }
    }

    @Override
    public void dischargePatients(Patient patient) {
        if(assignedPatients.contains(patient)){
            assignedPatients.remove(patient);
            patient.dischargePatient();
        }
        else{
            System.out.println("Doctor " + getName() + " is not assigned to this patient.");
        }
    }

    @Override
    public void viewAssignedPatients() {
        System.out.println("\nDoctor " + getName() + "'s Patients:");
        System.out.println("+----+------------+--------+");
        System.out.println("| ID | Name       | Status |");
        System.out.println("+----+------------+--------+");

        for (Patient p : assignedPatients) {
            System.out.printf("| %-2d | %-10s | %-6s |\n", p.getId(), p.getName(), p.getStatus());
        }

        System.out.println("+----+------------+--------+");
    }
}
