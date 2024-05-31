package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public static String validateInputForPattern(String userInput, String patternString, String validInput, Register register) {
        String input = checkRegister(userInput, register);
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);
        while (!matcher.matches()){
            System.out.println("The input '" + userInput + "' is not valid! Valid input is: " + validInput);
            System.out.print("Please try again: ");
            userInput = Main.SCANNER.nextLine();
            input = checkRegister(userInput, register);
            matcher = pattern.matcher(input);
        }
        return input;
    }

    public static String validateInputForPattern(String patternString, String validInput) {
        return validateInputForPattern(Main.SCANNER.nextLine(), patternString, validInput, Register.IGNORE);
    }

    public static String validateInputForPattern(String patternString, String validInput, Register register) {
        return validateInputForPattern(Main.SCANNER.nextLine(), patternString, validInput, register);
    }

    public enum Register {
        LOWER, UPPER, IGNORE
    }
    private static String checkRegister(String input, Register register) {
        switch (register) {
            case LOWER -> input = input.trim().toLowerCase();
            case UPPER -> input = input.trim().toUpperCase();
            case IGNORE -> input = input.trim();
        }
        return input;
    }
}
