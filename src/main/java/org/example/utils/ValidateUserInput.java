package org.example.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidateUserInput {
    public static int getValidInteger(Scanner scanner, String prompt) {
        int number;
        while (true) {
            System.out.print(prompt);
            try {
                number = scanner.nextInt();
                scanner.nextLine();
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    public static void validateStringInput(String string){
        if (string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException("Entered String can't be empty");
        }
    }
}
