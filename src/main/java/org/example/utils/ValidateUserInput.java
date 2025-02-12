package org.example.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Validation utility class for both integer and string inputs
 */
public class ValidateUserInput {
    /**
     * Ensures that the user can only enter valid integer number as an input
     * @param scanner Scanner object for taking input
     * @param prompt String for prompt message before taking the input
     * @return the user input integer
     * @throws  InputMismatchException when invalid input presented.
     */
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

    /**
     * Checks the string for null and Emptiness. Throws error if true.
     * @param string String to check
     * @throws IllegalArgumentException If string is empty.
     */
    public static void validateStringInput(String string){
        if (string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException("Entered String can't be empty");
        }
    }
}
