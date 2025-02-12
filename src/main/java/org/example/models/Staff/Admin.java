package org.example.models.Staff;

import org.example.interfaces.AdminActions;
import org.example.models.Hospital.Hospital;
import org.example.utils.ValidateUserInput;

/**
 * Admin Class for defining properties of the admin.
 * The admin will be a singleton class in the application.
 * It extends the Staff properties and are required to implement certain defined tasks defined in the AdminActions interface.
 */
public class Admin extends Staff implements AdminActions {
    // The static Admin object private for limited access ie to the admin itself.
    private static Admin instance;
    // the hospital object
    private final Hospital hospital;

    private Admin(Hospital hospital) {
        super("A", "admin", "admin");
        this.hospital = hospital;
    }

    /**
     * Returns the Admin object instance defined in the class.
     * if found null, new instance is created for the app
     * @param hospital
     * @return Admin object instance
     */
    public static Admin getInstance(Hospital hospital) {
        if (instance == null) {
            instance = new Admin(hospital);
        }
        return instance;
    }

    /**
     * set the hospital name. The entered string is validated for null safety
     * @param name: name of the hospital
     * @throws IllegalArgumentException when hospital name is invalid
     */
    public void setHospitalName(String name) {
        try {
            ValidateUserInput.validateStringInput(name);
            hospital.setName(name);
            System.out.println("Hospital name updated to: " + name);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid hospital name: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error changing hospital name: " + e.getMessage());
        }
    }

    /**
     * set the hospital address/ The entered string is validated for null safety
     * @param address: address of the hospital
     * @throws IllegalArgumentException when the address is invalid
     */
    public void setHospitalAddress(String address) {
        try {
            ValidateUserInput.validateStringInput(address);
            hospital.setAddress(address);
            System.out.println("Hospital address updated to: " + address);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid hospital address: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error changing hospital address: " + e.getMessage());
        }
    }

    /**
     * Creates new doctor object for the hospital. Both parameters are validated
     * @param name: name of the doctor
     * @param speciality: speciality of said doctor
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    public void addDoctor(String name, String speciality) {
        try {
            ValidateUserInput.validateStringInput(name);
            ValidateUserInput.validateStringInput(speciality);

            DoctorList doctorList = DoctorList.getInstance();

            Doctor doctor = new Doctor(name, speciality);
            doctorList.addDoctor(doctor);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while adding doctor: " + e.getMessage());
        }
    }

    /**
     * Creates new receptionist object for the hospital. name parameter is validated
     * @param name: name of the receptionist
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    public void addReceptionist(String name) {
        try {
            ValidateUserInput.validateStringInput(name);
            ReceptionistList receptionistList = ReceptionistList.getInstance();
            Receptionist receptionist = new Receptionist(name);
            receptionistList.addReceptionist(receptionist);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while adding receptionist: " + e.getMessage());
        }
    }

    @Override
    public void performDuties() {
        System.out.println("Admin is managing hospital details.");
    }
}
