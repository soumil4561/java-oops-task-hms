package org.example.models.Hospital;

import org.example.models.Patient.PatientList;

import java.util.Scanner;

public class Hospital {
    private final String name;
    private final String address;

    public Hospital(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        PatientList patientList = PatientList.getInstance();
        while(run){
            System.out.println("Welcome to the "+getName()+"\nAddress: "+getAddress());
            System.out.println("Who are you? (Enter number):\n1. Receptionist\n2. Doctor\n3. Quit");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("to the receptionist....");
                    break;
                case 2:
                    System.out.println("to the doctor....");
                    break;
                case 3:
                    System.out.println("Quiting....");
                    run = false;
                default:
                    break;
            }

        }
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
