package org.example.models.Staff;

import org.example.utils.ValidateUserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Singleton class maintaining the list of doctors in the system.
 */
public class DoctorList {
    private static volatile DoctorList instance; // Private static instance to enforce singleton pattern.
    private final List<Doctor> doctors; // List to store doctor objects.

    /**
     * Private constructor to prevent unwanted instance creation.
     */
    private DoctorList() {
        doctors = new ArrayList<>();
    }

    /**
     * Returns the single instance of the DoctorList class.
     * If the instance is null, it is created.
     *
     * @return DoctorList singleton instance.
     */
    public static DoctorList getInstance() {
        if (instance == null) {
            instance = new DoctorList();
        }
        return instance;
    }

    /**
     * Adds a doctor to the list.
     * If the doctor is null or already exists in the list, an error is logged.
     *
     * @param doctor The doctor to be added.
     * @throws IllegalArgumentException if the provided doctor is null.
     * @throws IllegalStateException    if the doctor is already added.
     */
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

    /**
     * Finds a doctor by name.
     * The name is validated before searching in the list.
     *
     * @param name The name of the doctor to be found.
     * @return The Doctor object if found, otherwise null.
     * @throws IllegalArgumentException if the name is invalid.
     * @throws NoSuchElementException   if no doctor with the given name is found.
     */
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

    /**
     * Finds a doctor by ID.
     * The ID is validated before searching in the list.
     *
     * @param id The ID of the doctor to be found.
     * @return The Doctor object if found, otherwise null.
     * @throws IllegalArgumentException if the ID is invalid.
     */
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

    /**
     * Returns a copy of the list of all doctors.
     *
     * @return A list of doctors.
     */
    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }

    /**
     * Prints the list of doctors in a tabular format.
     */
    public void viewStaffList() {
        System.out.println("\n-------- Listing Doctors --------");

        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        System.out.println("| ID   | Name       | Speciality    |");

        for (Doctor doctor : doctors) {
            System.out.printf("| %-4s | %-10s | %-13s |\n", doctor.getId(), doctor.getName(), doctor.getSpeciality());
        }
    }
}
