package org.example.models.Staff;

import org.example.utils.ValidateUserInput;

import java.util.ArrayList;
import java.util.List;

public class ReceptionistList {
    private static ReceptionistList instance;
    private final List<Receptionist> receptionists;

    private ReceptionistList() {
        receptionists = new ArrayList<>();
    }

    public static ReceptionistList getInstance() {
        if (instance == null) {
            instance = new ReceptionistList();
        }
        return instance;
    }

    public void addReceptionist(Receptionist receptionist) {
        try {
            if (receptionist == null) {
                throw new IllegalArgumentException("Receptionist cannot be null.");
            }

            if (findById(receptionist.getId()) != null) {
                System.out.println("Receptionist with ID " + receptionist.getId() + " already exists.");
                return;
            }

            receptionists.add(receptionist);
            System.out.println("Receptionist " + receptionist.getName() + " added successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while adding receptionist: " + e.getMessage());
        }
    }

    public Receptionist findById(String id) {
        ValidateUserInput.validateStringInput(id);

        return receptionists.stream()
                .filter(receptionist -> receptionist.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void viewStaffList() {
        if (receptionists.isEmpty()) {
            System.out.println("No receptionists available.");
            return;
        }

        System.out.println("\n--------Listing Receptionists---------- ");
        for (Receptionist receptionist : receptionists) {
            System.out.println(receptionist.getId() + ": " + receptionist.getName());
        }
    }
}
