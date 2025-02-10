package org.example.models.Patient;

import org.example.models.Staff.Doctor;
import org.example.utils.ValidateUserInput;

public class Patient {
    private final String id;
    private final String name;
    private final int age;
    private Doctor doctor;
    private String status = "OPD";
    private static int patientCounter = 1;

    public Patient(String name, int age) {
        ValidateUserInput.validateStringInput(name);
        //age check
        if (age <= 0 || age > 120) {
            throw new IllegalArgumentException("Error: Invalid age provided.");
        }

        this.id = "P" + patientCounter;
        this.name = name;
        this.age = age;

        patientCounter++;
    }

    public void addPatient() {
        try {
            PatientList patientList = PatientList.getInstance();
            patientList.addOPDPatient(this);
        } catch (Exception e) {
            System.out.println("Error: Unable to add patient. " + e.getMessage());
        }
    }

    public void markAsInPatient() {
        if (this.getStatus().equals("OPD")) {
            this.setStatus("IPD");
            try {
                PatientList patientList = PatientList.getInstance();
                patientList.upgradeToIPD(this);
                System.out.println("Patient " + this.getName() + " admitted.");
            } catch (Exception e) {
                System.out.println("Error: Unable to mark patient as in-patient. " + e.getMessage());
            }
        } else {
            System.out.println("Invalid Operation: Patient is not in OPD.");
        }
    }

    public void dischargePatient() {
        if (this.getStatus().equals("OPD") || this.getStatus().equals("IPD")) {
            try {
                PatientList patientList = PatientList.getInstance();
                patientList.dischargePatient(this);
            } catch (Exception e) {
                System.out.println("Error: Unable to discharge patient. " + e.getMessage());
            }
        } else {
            System.out.println("Error: Patient not found in any active list.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || (!status.equals("OPD") && !status.equals("IPD") && !status.equals("Discharged"))) {
            System.out.println("Error: Invalid patient status update.");
            return;
        }
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        if (doctor == null) {
            System.out.println("Error: Cannot assign a null doctor.");
            return;
        }
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", doctor=" + (doctor != null ? doctor.getName() : "None") +
                ", status='" + status + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }
}
