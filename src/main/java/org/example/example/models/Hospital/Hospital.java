package org.example.models.Hospital;

import org.example.models.Patient.PatientList;
import org.example.utils.SampleDataLoader;
import org.example.utils.ValidateUserInput;
import org.example.view.MainMenu;

import org.example.utils.ConfigLoader;

public class Hospital {
    private String name;
    private String address;

    /**
     * The hospital constructor with address and name which it preloads from the application.properties file
     */
    public Hospital() {
        this.name = ConfigLoader.getDefaultHospitalName();
        this.address = ConfigLoader.getDefaultHospitalAddress();
    }

    /**
     * Start function, loads sample data from Sample Data Loader
     */
    public void start(){
        SampleDataLoader.loadSampleData();
        PatientList patientList = PatientList.getInstance();
        new MainMenu(this).display();
    }

    //Getter-Setters
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
