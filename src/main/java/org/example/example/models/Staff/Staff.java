package org.example.models.Staff;

/**
 * Abstract class representing a staff member in the hospital management system.
 * This serves as a base class for specific roles such as doctors and receptionists.
 */
public abstract class Staff {
    private final String id;    // Unique identifier for the staff member
    private final String name;  // Name of the staff member
    private final String role;  // Role of the staff member (e.g., Doctor, Receptionist)

    /**
     * Constructs a new Staff member with the given ID, name, and role.
     *
     * @param id   Unique identifier for the staff.
     * @param name Name of the staff member.
     * @param role Role of the staff (e.g., "Doctor", "Receptionist").
     */
    public Staff(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    /**
     * Gets the name of the staff member.
     *
     * @return The name of the staff member.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the role of the staff member.
     *
     * @return The role of the staff member.
     */
    public String getRole() {
        return role;
    }

    /**
     * Gets the unique id of the staff member.
     *
     * @return The ID of the staff member.
     */
    public String getId() {
        return id;
    }

    /**
     * Abstract method that must be implemented by subclasses to define the
     * specific duties of each type of staff member.
     */
    public abstract void performDuties();

    /**
     * Returns a formatted string representation of the staff member's details.
     *
     * @return A formatted string containing the staff ID, name, and role.
     */
    @Override
    public String toString() {
        return String.format("| %-3s | %-15s | %-12s |", id, name, role);
    }
}
