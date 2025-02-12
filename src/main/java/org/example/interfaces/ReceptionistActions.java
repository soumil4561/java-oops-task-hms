package org.example.interfaces;

import org.example.models.Staff.Receptionist;
import org.example.models.Patient.Patient;
import org.example.models.Staff.Doctor;

public interface ReceptionistActions {
    /**
     * Register a new patient in the hospital system.
     * @param patient The patient to be registered.
     */
    void registerPatient(Patient patient);

    /**
     * Assign a patient to a specific doctor for consultation or treatment.
     * @param patient The patient to be assigned.
     * @param doctor The doctor to whom the patient is assigned.
     */
    void assignPatientToDoctor(Patient patient, Doctor doctor);
}
