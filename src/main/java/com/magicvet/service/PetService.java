package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.model.Pet;


public class PetService {
    private static final String DOG_TYPE = "dog";
    private static final String CAT_TYPE = "cat";
    private static final String AGE_PATTERN = "^(\\d+\\s*(y|year|years)$|" +
                                                "\\d+\\s*(m|month|months)$|" +
                                                "\\d+\\s*(y|year|years)\\s*(and)*\\s*\\d+\\s*(m|month|months)$)";
    private static final String PET_NAME_PATTERN = "[\\s\\S]*\\S[\\s\\S]*";
    private static final String SEX_PATTERN = "^(male|female|m|f)$";
    private static final String SIZE_PATTERN = "^(xs|s|m|l|xl)$";
    private static final String HEALTH_PATTERN = "^(critical|serious|moderate|healthy|c|s|m|h)$";

    public Pet registerNewPet() {
        Pet pet = null;
        System.out.print("Type (dog / cat): ");

        String type = InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),"(dog|cat)",
                "dog or cat");
        pet = buildPet(type);
        return pet;
    }

    private Pet buildPet(String type) {
        Pet pet = type.equals(CAT_TYPE) ? new Cat() : new Dog();
        pet.setType(type);

        System.out.print("Age: (For ex: 5 years / 3 months/ 2 years and 1 month) ");
        pet.setAge(InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),AGE_PATTERN,
                " 5 years / 3 months/ 2 years and 1 month"));

        System.out.print("Name: ");
        pet.setName(InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),PET_NAME_PATTERN,
                "at least one non whitespace character"));

        System.out.print("Sex (male / female): ");
        pet.setSex(InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),SEX_PATTERN,
                "male or m / female or f"));

        if (type.equals(DOG_TYPE)){
            System.out.print("Size (XS / S / M / L / XL): ");
            String size = InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),SIZE_PATTERN,
                    "XS / S / M / L / XL");
            ((Dog) pet).setSize(Dog.Size.valueOf(size.toUpperCase()));
        }

        System.out.print("What is your view of your pet's health status " +
                "(CRITICAL(C) / SERIOUS(S) / MODERATE(M) / HEALTHY(H)): ");
        String healthStatus = InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),HEALTH_PATTERN,
                "CRITICAL or C / SERIOUS or S / MODERATE or M / HEALTHY or H");
        switch (healthStatus.toUpperCase()) {
            case "CRITICAL","C" -> pet.setHealthState(Pet.HealthStatus.CRITICAL);
            case "SERIOUS","S" -> pet.setHealthState(Pet.HealthStatus.SERIOUS);
            case "MODERATE","M" -> pet.setHealthState(Pet.HealthStatus.MODERATE);
            case "HEALTHY","H" -> pet.setHealthState(Pet.HealthStatus.HEALTHY);
        }
        return pet;
    }



}
