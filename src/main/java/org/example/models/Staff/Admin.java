package org.example.models.Staff;

import org.example.interfaces.AdminActions;
import org.example.models.Hospital.Hospital;
import org.example.utils.ValidateUserInput;

public class Admin extends Staff implements AdminActions {
    private static Admin instance;
    private final Hospital hospital;

    private Admin(Hospital hospital) {
        super("A", "admin", "admin");
        this.hospital = hospital;
    }

    public static Admin getInstance(Hospital hospital) {
        if (instance == null) {
            instance = new Admin(hospital);
        }
        return instance;
    }

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
