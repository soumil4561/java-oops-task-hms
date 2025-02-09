package org.example.interfaces;

import org.example.models.Staff.Receptionist;
import org.example.models.Patient.Patient;
import org.example.models.Staff.Doctor;

public interface ReceptionistActions {
    void registerPatient(Patient patient);
    void assignPatientToDoctor(Patient patient, Doctor doctor);
}