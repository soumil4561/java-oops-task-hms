package org.example.models.Patient;

import org.example.utils.ValidateUserInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class maintaining list of different status patients.
 */
public class PatientList {
    // OPD Patient List
    static List<Patient> OPDPatientList;
    // IPD Patient List
    static List<Patient> IPDPatientList;
    // Discharged Patient List
    static List<Patient> DischargedList;

    //static instance of patient list that is provided to each caller for global patient list
    private static PatientList instance;

    /**
     * private constructor to avoid multiple object creation
     */
    private PatientList(){
        OPDPatientList = new ArrayList<>();
        IPDPatientList = new ArrayList<>();
        DischargedList = new ArrayList<>();
    }

    /**
     * Returns the PatientList object instance defined above to each caller.
     * If found null, new instance is created for the app.
     * @return Patient list object named "instance"
     */
    public static PatientList getInstance() {
        if (instance == null) {
            instance = new PatientList();
        }
        return instance;
    }

    /**
     * Adds Patient to OPD List
     * @param patient
     */
    public void addOPDPatient(Patient patient){
        if(patient==null){
            System.err.println("Error: Patient cant be null");
            return;
        }
        OPDPatientList.add(patient);
        System.out.println("Added patient "+patient.getName()+ " to OPD List");
    }

    /**
     * Upgrades an OPD patient to IPD Status. Removes patient from OPD List and puts in IPD List
     * @param patient
     */
    public void upgradeToIPD(Patient patient){
        if(patient==null){
            System.err.println("Error: Patient cant be null");
            return;
        }
        if(OPDPatientList.contains(patient)){
            OPDPatientList.remove(patient);
            System.out.println("Removed patient from OPD List");
            IPDPatientList.add(patient);
            System.out.println("Added patient "+patient.getName()+ " to IPD List");
        }
        else System.out.println("Patient not found in OPD List");
    }

    /**
     * Removes patient from OPD/IPD patient list and puts them in the discharged list
     * Updates patient status to Discharged
     * @param patient
     */
    public void dischargePatient(Patient patient){
        if(patient==null){
            System.err.println("Error: Patient cant be null");
            return;
        }
        if(patient.getStatus().equals("OPD")){
            OPDPatientList.remove(patient);
        }
        else if(patient.getStatus().equals("IPD")){
            IPDPatientList.remove(patient);
        }
        patient.setStatus("Discharged");
        DischargedList.add(patient);
        System.out.println("Patient "+patient.getName()+ " transferred to Discharged List!");
    }

    /**
     * Getter for OPD Patient List
     * @return OPD Patient List
     */
    public List<Patient> getOPDPatientList() {
        return OPDPatientList;
    }

    /**
     * Getter for IPD Patient List
     * @return IPD Patient List
     */
    public List<Patient> getIPDPatientList() {
        return IPDPatientList;
    }

    /**
     * Getter for Discharged Patient List
     * @return Discharged Patient List
     */
    public List<Patient> getDischargedList() {
        return DischargedList;
    }

    /**
     * Find patient by the given Patient ID. The pateint is first searched in the OPD list,
     * then the IPD List and finally the discharged list.
     * @param patientID
     * @return Patient
     */
    public Patient findById(String patientID){
        System.out.println("Searching ID: "+patientID);
        try{
            Patient patient= findByIDinOPDList(patientID);
            if(patient!=null) return patient;

            patient = findByIDinIPDList(patientID);
            if(patient!=null) return patient;

            return findByIDinDischargedList(patientID);
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: "+e.getMessage());
            return null;
        }
    }

    /**
     * Find patient by the given Patient ID in the OPD List
     * @param patientID
     * @return Patient
     */
    public Patient findByIDinOPDList(String patientID) {
        ValidateUserInput.validateStringInput(patientID);
        return OPDPatientList.stream()
                .filter(patient -> patient.getId().equals(patientID))
                .findFirst().orElse(null);
    }

    /**
     * Find patient by the given Patient ID in the IPD List
     * @param patientID
     * @return Patient
     */
    public Patient findByIDinIPDList(String patientID) {
        ValidateUserInput.validateStringInput(patientID);
        return IPDPatientList.stream()
                .filter(patient -> patient.getId().equals(patientID))
                .findFirst().orElse(null);
    }
    /**
     * Find patient by the given Patient ID in the Discharged List
     * @param patientID
     * @return Patient
     */
    public Patient findByIDinDischargedList(String patientID){
        ValidateUserInput.validateStringInput(patientID);
        return DischargedList.stream()
                .filter(p->p.getId().equals(patientID))
                .findFirst().orElse(null);
    }
}
