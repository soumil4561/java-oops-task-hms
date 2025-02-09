package org.example.models.Staff;

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
        receptionists.add(receptionist);
    }

    public List<Receptionist> getReceptionists() {
        return receptionists;
    }
}
