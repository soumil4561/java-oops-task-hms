package org.example.models.Staff;

import java.util.ArrayList;
import java.util.List;

public class DoctorList {
    private static DoctorList instance;
    private final List<Doctor> doctors;

    private DoctorList() {
        doctors = new ArrayList<>();
    }

    public static DoctorList getInstance() {
        if (instance == null) {
            instance = new DoctorList();
        }
        return instance;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public Doctor findByName(String name) {
        return doctors.stream()
                .filter(doctor -> doctor.getName().equals(name))
                .findFirst()
                .orElse(null);
    }


    public List<Doctor> getDoctors() {
        return doctors;
    }
}
