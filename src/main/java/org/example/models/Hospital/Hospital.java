package org.example.models.Hospital;

import org.example.models.Patient.PatientList;
import org.example.utils.SampleDataLoader;
import org.example.utils.ValidateUserInput;
import org.example.view.MainMenu;

import org.example.utils.ConfigLoader;

public class Hospital {
    private String name;
    private String address;

    public Hospital() {
        this.name = ConfigLoader.getDefaultHospitalName();
        this.address = ConfigLoader.getDefaultHospitalAddress();
    }

    public void start(){
        SampleDataLoader.loadSampleData();
        PatientList patientList = PatientList.getInstance();
        new MainMenu(this).display();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        ValidateUserInput.validateStringInput(name);
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
