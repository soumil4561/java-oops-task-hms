package org.example.interfaces;

import org.example.models.Patient.Patient;

public interface DoctorActions {
    void admitPatient(Patient patient);
    void dischargePatients(Patient patient);
    void viewAssignedPatients();
}
