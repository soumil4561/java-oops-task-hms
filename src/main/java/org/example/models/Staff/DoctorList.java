package org.example.models.Staff;

import org.example.utils.ValidateUserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DoctorList {
    private static volatile DoctorList instance;
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
        try {
            if (doctor == null) {
                throw new IllegalArgumentException("Doctor cannot be null.");
            }

            if (doctors.contains(doctor)) {
                throw new IllegalStateException("Doctor " + doctor.getName() + " is already added.");
            }

            doctors.add(doctor);
            System.out.println("Doctor " + doctor.getName() + " (" + doctor.getSpeciality() + ") added successfully.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while adding doctor: " + e.getMessage());
        }
    }

    public Doctor findByName(String name) {
        try {
            ValidateUserInput.validateStringInput(name);

            return doctors.stream()
                    .filter(doctor -> doctor.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No doctor found with name: " + name));

        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Unexpected error while searching for doctor: " + e.getMessage());
            return null;
        }
    }

    public Doctor findByID(String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("Doctor ID cannot be null or empty.");
            }

            return doctors.stream()
                    .filter(doctor -> doctor.getId().equalsIgnoreCase(id))
                    .findFirst()
                    .orElse(null);

        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Unexpected error while searching for doctor: " + e.getMessage());
            return null;
        }
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }

    public void viewStaffList() {
        System.out.println("\n-------- Listing Doctors --------");

        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }
        System.out.println("| ID | Name       | Speciality    |");

        for (Doctor doctor : doctors) {
            System.out.printf("| %-4s | %-10s | %-13s |\n", doctor.getId(), doctor.getName(), doctor.getSpeciality());
        }
    }
}
