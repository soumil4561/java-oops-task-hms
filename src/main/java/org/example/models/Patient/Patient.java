package org.example.models.Patient;

import org.example.models.Staff.Doctor;

public class Patient {
    private final int id;
    private final String name;
    private final int age;
    private Doctor doctor;
    private String status = "OPD";

    public Patient(String name, int age) {
        this.id = 1;
        this.name = name;
        this.age = age;
    }

    public void addPatient(){
        PatientList patientList = PatientList.getInstance();
        patientList.addOPDPatient(this);
    }

    public void markAsInPatient(){
        if(this.getStatus().equals("OPD")){
            this.setStatus("IPD");
            PatientList patientList = PatientList.getInstance();
            patientList.upgradeToIPD(this);
            System.out.println("Patient "+this.getName()+" admitted");
        }
        else System.out.println("Invalid Operation");
    }

    public void dischargePatient(){
        if(this.getStatus().equals("OPD") || this.getStatus().equals("IPD")){
            PatientList patientList = PatientList.getInstance();
            patientList.dischargePatient(this);
        }
        else System.out.println("Patient not found in any active list.");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", doctor=" + doctor +
                ", status='" + status + '\'' +
                '}';
    }

    public Object getId() {
        return id;
    }
}
