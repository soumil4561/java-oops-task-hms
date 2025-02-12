package org.example.interfaces;

public interface AdminActions {
    /**
     * Change the hospital name
     * @param name The new hospital name
     */
    void setHospitalName(String name);

    /**
     * Change the address of the hospital
     * @param address The new hospital address
     */
    void setHospitalAddress(String address);

    /**
     * Register a new doctor in the system
     * @param name Name of the doctor (String)
     * @param speciality String, one word , describe their specialization
     */
    void addDoctor(String name, String speciality);

    /**
     * Register a new receptionist in the system
     * @param name Name of the receptionist (string)
     */
    void addReceptionist(String name);
}
