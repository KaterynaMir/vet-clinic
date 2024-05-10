package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.model.Pet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetService {
    private static final String DOG_TYPE = "dog";
    private static final String CAT_TYPE = "cat";
    private static final String AGE_PATTERN = "^(\\d+\\s*(y|year|years)$|" +
                                                "\\d+\\s*(m|month|months)$|" +
                                                "\\d+\\s*(y|year|years)\\s*(and)*\\s*\\d+\\s*(m|month|months)$)";

    private static final String SEX_PATTERN = "^(male|female|m|f)$";
    private static final String SIZE_PATTERN = "^(XS|S|M|L|XL)$";
    private static final String HEALTH_PATTERN = "^(CRITICAL|SERIOUS|MODERATE|HEALTHY|C|S|M|H)$";

    public Pet registerNewPet() {
        Pet pet = null;
        System.out.print("Type (dog / cat): ");

        String type = Main.SCANNER.nextLine().trim().toLowerCase();

        if (DOG_TYPE.equals(type) || CAT_TYPE.equals(type)) {
            pet = buildPet(type);
        } else {
            System.out.println("Unknown pet type: " + type);
        }
        return pet;
    }

    private Pet buildPet(String type) {
        Pet pet = type.equals(CAT_TYPE) ? new Cat() : new Dog();
        pet.setType(type);

        System.out.print("Age: (For ex: 5 years / 3 months/ 2 years and 1 month) ");
        pet.setAge(validateInputForPattern(Main.SCANNER.nextLine(),AGE_PATTERN," 5 years / 3 months/ 2 years and 1 month"));

        System.out.print("Name: ");
        pet.setName(Main.SCANNER.nextLine().trim());

        System.out.print("Sex (male / female): ");
        pet.setSex(validateInputForPattern(Main.SCANNER.nextLine(),SEX_PATTERN,"male or m / female or f"));

        if (type.equals(DOG_TYPE)){
            System.out.print("Size (XS / S / M / L / XL): ");
            String size = validateInputForPattern(Main.SCANNER.nextLine(),SIZE_PATTERN,"XS / S / M / L / XL");
            ((Dog) pet).setSize(Dog.Size.valueOf(size));
        }

        System.out.print("What is your view of your pet's health status (CRITICAL(C) / SERIOUS(S) / MODERATE(M) / HEALTHY(H)): ");
        String healthStatus = validateInputForPattern(Main.SCANNER.nextLine(),HEALTH_PATTERN,"CRITICAL or C /" +
                " SERIOUS or S / MODERATE or M / HEALTHY or H");
        switch (healthStatus) {
            case "CRITICAL","C" -> pet.setHealthState(Pet.HealthStatus.CRITICAL);
            case "SERIOUS","S" -> pet.setHealthState(Pet.HealthStatus.SERIOUS);
            case "MODERATE","M" -> pet.setHealthState(Pet.HealthStatus.MODERATE);
            case "HEALTHY","H" -> pet.setHealthState(Pet.HealthStatus.HEALTHY);
        }
        return pet;
    }

    private String validateInputForPattern(String input, String patternString, String validInput) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input.trim());
        while (!matcher.matches()){
            System.out.println("The input '" + input + "' is not valid! Valid input is: " + validInput);
            System.out.print("Please try again: ");
            input = Main.SCANNER.nextLine();
            matcher = pattern.matcher(input.trim());
        }
        return input.trim();
    }

}
