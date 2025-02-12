package org.example.models.Staff;

import org.example.utils.ValidateUserInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class maintaining the list of receptionist in the system
 */
public class ReceptionistList {
    private static ReceptionistList instance;   // the private static object instance of the class
    private final List<Receptionist> receptionists; // the list of Receptionist

    /**
     * private constructor to prevent unwanted instance creation
     */
    private ReceptionistList() {
        receptionists = new ArrayList<>();
    }

    /**
     * Returns instance of the class which can be used by the caller. if found null, new instance is created.
     * @return ReceptionList object instance
     */
    public static ReceptionistList getInstance() {
        if (instance == null) {
            instance = new ReceptionistList();
        }
        return instance;
    }

    /**
     * Adds a receptionist to the list. If receptionist with same id found, it logs error in console and returns
     * @param receptionist
     * @throws IllegalArgumentException when giving null receptionist
     */
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

    /**
     * finds receptionist by ID in the list using stream api. The id is first validated for null safety
     * @param id
     * @return  Receptionist to be found
     */
    public Receptionist findById(String id) {
        ValidateUserInput.validateStringInput(id);

        return receptionists.stream()
                .filter(receptionist -> receptionist.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Prints the receptionist list in the console.
     */
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
