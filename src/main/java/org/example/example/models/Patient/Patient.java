package org.example.models.Patient;

import org.example.models.Staff.Doctor;
import org.example.utils.ValidateUserInput;

/**
 * THe Patient Class for creating patient objects through the system
 */
public class Patient {
    // Patient ID for each patient Autogenerated
    private final String id;
    // Patient name
    private final String name;
    //Patient age
    private final int age;
    //The Doctor assigned to them
    private Doctor doctor;
    /**
     * Patient status: Varies b/w OPD (OutPatient Department), IPD (InPatient Department) and Discharged
     */
    private String status = "OPD";
    //Static counter for generating patient IDs in the system
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

    /**
     * Add the current patient to the Patient list.
     * Done by the receptionist. The function adds the list by default to the OPD List (as first consultation goes)
     */
    public void addPatient() {
        try {
            PatientList patientList = PatientList.getInstance();
            patientList.addOPDPatient(this);
        } catch (Exception e) {
            System.out.println("Error: Unable to add patient. " + e.getMessage());
        }
    }

    /**
     * Change the status of a patient to IPD from OPD.
     */
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

    /**
     * Discharges an OPD/IPD status patient and invokes the patientList function to discharge patient accordingly
     */
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
