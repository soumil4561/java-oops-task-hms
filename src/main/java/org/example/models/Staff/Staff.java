package org.example.models.Staff;

public abstract class Staff {
    private final String id;
    private final String name;
    private final String role;

    public Staff(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getId() {
        return id;
    }

    public abstract void performDuties();

    @Override
    public String toString() {
        return String.format("| %-3d | %-15s | %-12s |", id, name, role);
    }
}
