package org.example.interfaces;

import org.example.models.Patient.Patient;

public interface DoctorActions {
    /**
     * Admit a patient to the hospital.
     * @param patient The patient to be admitted.
     */
    void admitPatient(Patient patient);

    /**
     * Discharge a patient from the hospital.
     * @param patient The patient to be discharged.
     */
    void dischargePatients(Patient patient);

    /**
     * View the list of patients assigned to the doctor.
     */
    void viewAssignedPatients();
}
