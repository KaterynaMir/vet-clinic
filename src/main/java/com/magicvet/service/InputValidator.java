package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public static String validateInputForPattern(String input, String patternString, String validInput) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(prepareInputForMatch(input,patternString));
        while (!matcher.matches()){
            System.out.println("The input '" + input + "' is not valid! Valid input is: " + validInput);
            System.out.print("Please try again: ");
            input = Main.SCANNER.nextLine();
            matcher = pattern.matcher(prepareInputForMatch(input,patternString));
        }
        return prepareInputForMatch(input,patternString);
    }

    private static String prepareInputForMatch (String input, String patternString) {
        String patternStringWithoutRegexMetaChar = patternString.replaceAll(
                "\\\\(d|D|s|S|t|n|w|W|b|B|Q|E|A|Z|z|G)","");
        if (patternStringWithoutRegexMetaChar.equals(patternStringWithoutRegexMetaChar.toLowerCase())) {
            return input.trim().toLowerCase();
        } else if (patternStringWithoutRegexMetaChar.equals(patternStringWithoutRegexMetaChar.toUpperCase())){
            return input.trim().toUpperCase();
        } else {
            return input.trim();
        }
    }
}
