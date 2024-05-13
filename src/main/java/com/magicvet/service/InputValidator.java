package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public static String validateInputForPattern(String input, String patternString, String validInput) {
        input = input.trim().toLowerCase();
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);
        while (!matcher.matches()){
            System.out.println("The input '" + input + "' is not valid! Valid input is: " + validInput);
            System.out.print("Please try again: ");
            input = Main.SCANNER.nextLine().trim().toLowerCase();
            matcher = pattern.matcher(input);
        }
        return input;
    }
}
