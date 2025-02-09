package org.example.models.Staff;

import org.example.interfaces.AdminActions;
import org.example.models.Hospital.Hospital;

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
        hospital.setName(name);
        System.out.println("Hospital name updated to: " + name);
    }

    public void setHospitalAddress(String address) {
        hospital.setAddress(address);
        System.out.println("Hospital address updated to: " + address);
    }

    public void addDoctor(String name, String speciality) {
        Doctor doctor = new Doctor( name, speciality);
        DoctorList.getInstance().addDoctor(doctor);
        System.out.println("Doctor " + name + " added successfully.");
    }

    public void addReceptionist(String name) {
        Receptionist receptionist = new Receptionist(name);
        ReceptionistList.getInstance().addReceptionist(receptionist);
        System.out.println("Receptionist " + name + " added successfully.");
    }

    @Override
    public void performDuties() {
        System.out.println("Admin is managing hospital details");
    }
}
