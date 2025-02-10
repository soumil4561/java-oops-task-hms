package org.example.models.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientList {
    static List<Patient> OPDPatientList;
    static List<Patient> IPDPatientList;
    static List<Patient> DischargedList;

    private static PatientList instance;

    private PatientList(){
        OPDPatientList = new ArrayList<>();
        IPDPatientList = new ArrayList<>();
        DischargedList = new ArrayList<>();
    }

    public static PatientList getInstance() {
        if (instance == null) {
            instance = new PatientList(); // Initialize instance here
        }
        return instance;
    }

    public void addOPDPatient(Patient patient){
        OPDPatientList.add(patient);
        System.out.println("Added patient "+patient.getName()+ " to OPD List");
    }

    public void upgradeToIPD(Patient patient){
        if(OPDPatientList.contains(patient)){
            OPDPatientList.remove(patient);
            System.out.println("Removed patient from OPD List");
            IPDPatientList.add(patient);
            System.out.println("Added patient "+patient.getName()+ " to IPD List");
        }
        else System.out.println("Patient not found in OPD List");
    }

    public void dischargePatient(Patient patient){
        if(patient.getStatus().equals("OPD")){
            OPDPatientList.remove(patient);
        }
        else if(patient.getStatus().equals("IPD")){
            IPDPatientList.remove(patient);
        }
        patient.setStatus("Discharged");
        DischargedList.add(patient);
        System.out.println("Patient "+patient.getName()+ " discharged!");
    }

    public List<Patient> getOPDPatientList() {
        return OPDPatientList;
    }

    public List<Patient> getIPDPatientList() {
        return IPDPatientList;
    }

    public List<Patient> getDischargedList() {
        return DischargedList;
    }

    public Patient findById(String patientID){
        System.out.println("Searching ID: "+patientID);

        Patient patient= findByIDinOPDList(patientID);
        if(patient!=null) return patient;

        patient = findByIDinIPDList(patientID);
        if(patient!=null) return patient;

        return findByIDinDischargedList(patientID);
    }

    public Patient findByIDinOPDList(String patientID) {
        System.out.println("Searching ID: "+patientID);
        return OPDPatientList.stream()
                .filter(patient -> patient.getId().equals(patientID))
                .findFirst().orElse(null);
    }

    public Patient findByIDinIPDList(String patientID) {
        System.out.println("Searching ID: "+patientID);
        return IPDPatientList.stream()
                .filter(patient -> patient.getId().equals(patientID))
                .findFirst().orElse(null);
    }

    public Patient findByIDinDischargedList(String patientID){
        return DischargedList.stream()
                .filter(p->p.getId().equals(patientID))
                .findFirst().orElse(null);
    }
}
