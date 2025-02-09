package org.example.models.Staff;

import org.example.interfaces.ReceptionistActions;
import org.example.models.Patient.Patient;
import org.example.models.Patient.PatientList;

public class Receptionist extends Staff implements ReceptionistActions {
    public static int receptionistCounter = 1;

    public Receptionist(String name){
        super("R"+receptionistCounter ,name, "Receptionist");
        receptionistCounter++;
    }

    @Override
    public void registerPatient(Patient patient) {
        patient.addPatient();
    }

    @Override
    public void assignPatientToDoctor(Patient patient, Doctor doctor) {
        doctor.addPatienttoList(patient);
        System.out.println("Receptionist "+getName()+" assigned patient " + patient.getName() + " to Doctor " + doctor.getName());
    }

    @Override
    public void performDuties() {
        System.out.println(getName() + " is scheduling appointments.");
    }
}
