package org.example.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.models.Patient.Patient;
import org.example.models.Patient.PatientList;
import org.example.models.Staff.Doctor;
import org.example.models.Staff.DoctorList;
import org.example.models.Staff.Receptionist;
import org.example.models.Staff.ReceptionistList;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Sample data loader to initially populate the application at the start
 * The data is referred from json file placed in the resource folder name 'sample.json'
 * The data is read using the gson library
 */
public class SampleDataLoader {
    /**
     * Static data loader for the application
     */
    public static void loadSampleData() {
        // Check if sample data should be loaded
        if(!ConfigLoader.loadSampleData()) return;

        try (InputStream inputStream = SampleDataLoader.class.getClassLoader().getResourceAsStream("sample.json")) {
            if (inputStream == null) {
                System.err.println("Sample data file not found!");
                return;
            }

            JsonObject root = JsonParser.parseReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).getAsJsonObject();
            //loading all doctors as an array
            JsonArray doctorsArray = root.getAsJsonArray("doctors");
            if (doctorsArray != null) {
                for (var element : doctorsArray) {
                    JsonObject doctorObj = element.getAsJsonObject();
                    String name = doctorObj.get("name").getAsString();
                    String speciality = doctorObj.get("speciality").getAsString();
                    DoctorList.getInstance().addDoctor(new Doctor(name, speciality));
                }
            }
            //loading all receptionists as an array
            JsonArray receptionistsArray = root.getAsJsonArray("receptionists");
            if (receptionistsArray != null) {
                for (var element : receptionistsArray) {
                    JsonObject receptionistObj = element.getAsJsonObject();
                    String name = receptionistObj.get("name").getAsString();
                    ReceptionistList.getInstance().addReceptionist(new Receptionist(name));
                }
            }

            //loading all patients as an array
            JsonArray patientsArray = root.getAsJsonArray("patients");
            if (patientsArray != null) {
                for (var element : patientsArray) {
                    JsonObject patientObj = element.getAsJsonObject();
                    String name = patientObj.get("name").getAsString();
                    int age = patientObj.get("age").getAsInt();
                    String status = patientObj.get("status").getAsString();
                    String doctorName = patientObj.get("doctor").getAsString();
                    //finding the doctors mentioned in the json in the doctor list by name
                    Doctor assignedDoctor = DoctorList.getInstance().findByName(doctorName);
                    if (assignedDoctor != null) {
                        Patient patient = new Patient(name, age);
                        patient.setDoctor(assignedDoctor);
                        patient.setStatus("OPD");
                        assignedDoctor.addPatientToList(patient);
                        PatientList.getInstance().addOPDPatient(patient);
                        if(status.equals("IPD")) assignedDoctor.admitPatient(patient);
                        if(status.equals("Discharged")) assignedDoctor.dischargePatients(patient);
                    } else {
                        System.err.println("Doctor " + doctorName + " not found for patient " + name);
                    }
                }
            }

            System.out.println("Sample data loaded successfully.");
        } catch (Exception e) {
            System.err.println("Error loading sample data: " + e.getMessage());
        }
    }
}
