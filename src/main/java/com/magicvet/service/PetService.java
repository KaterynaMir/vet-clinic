package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.model.Pet;

import java.time.LocalDate;

import static main.java.com.magicvet.model.Pet.FORMATTER_BIRTH_DATE;


public class PetService {
    private static final String DOG_TYPE = "dog";
    private static final String CAT_TYPE = "cat";
    private static final String BIRTH_DATE_PATTERN = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(\\d){4}$";
    private static final String PET_NAME_PATTERN = "[\\s\\S]*\\S[\\s\\S]*";
    private static final String SEX_PATTERN = "^(male|female|m|f)$";
    private static final String SIZE_PATTERN = "^(XS|S|M|L|XL)$";
    private static final String HEALTH_PATTERN = "^(CRITICAL|SERIOUS|MODERATE|HEALTHY|C|S|M|H)$";

    public Pet registerNewPet() {
        Pet pet = null;
        System.out.print("Type (dog / cat): ");

        String type = InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),"(dog|cat)",
                "dog or cat", InputValidator.Register.LOWER);
        pet = buildPet(type);
        return pet;
    }

    private Pet buildPet(String type) {
        Pet pet = type.equals(CAT_TYPE) ? new Cat() : new Dog();

        System.out.print("Birth date in format dd/MM/yyyy:  ");
        String birthDateString = InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),BIRTH_DATE_PATTERN,
                "dd/MM/yyyy", InputValidator.Register.IGNORE);
        LocalDate birthDate = LocalDate.parse(birthDateString,FORMATTER_BIRTH_DATE);
        while (birthDate.isAfter(LocalDate.now())) {
            System.out.print("You entered future date! Please, enter correct birth date for your pet: ");
            birthDateString = InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),BIRTH_DATE_PATTERN,
                    "dd/MM/yyyy (dd = 01-31, MM = 01-12)", InputValidator.Register.IGNORE);
            birthDate = LocalDate.parse(birthDateString,FORMATTER_BIRTH_DATE);
        }
        pet.setBirthDate(birthDate);

        System.out.print("Name: ");
        pet.setName(InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),PET_NAME_PATTERN,
                "at least one non whitespace character", InputValidator.Register.IGNORE));

        System.out.print("Sex (male / female): ");
        pet.setSex(InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),SEX_PATTERN,
                "male or m / female or f", InputValidator.Register.LOWER));

        if (type.equals(DOG_TYPE)){
            System.out.print("Size (XS / S / M / L / XL): ");
            String size = InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),SIZE_PATTERN,
                    "XS / S / M / L / XL", InputValidator.Register.UPPER);
            ((Dog) pet).setSize(Dog.Size.valueOf(size.toUpperCase()));
        }

        System.out.print("What is your view of your pet's health status " +
                "(CRITICAL(C) / SERIOUS(S) / MODERATE(M) / HEALTHY(H)): ");
        String healthStatus = InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),HEALTH_PATTERN,
                "CRITICAL or C / SERIOUS or S / MODERATE or M / HEALTHY or H", InputValidator.Register.UPPER);
        switch (healthStatus.toUpperCase()) {
            case "CRITICAL","C" -> pet.setHealthState(Pet.HealthStatus.CRITICAL);
            case "SERIOUS","S" -> pet.setHealthState(Pet.HealthStatus.SERIOUS);
            case "MODERATE","M" -> pet.setHealthState(Pet.HealthStatus.MODERATE);
            case "HEALTHY","H" -> pet.setHealthState(Pet.HealthStatus.HEALTHY);
        }
        return pet;
    }



}
