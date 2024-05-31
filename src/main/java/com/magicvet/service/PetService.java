package main.java.com.magicvet.service;

import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.model.Pet;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

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
        System.out.print("Type (dog / cat): ");

        String type = InputValidator.validateInputForPattern("(dog|cat)",
                "dog or cat", InputValidator.Register.LOWER);
        return buildPet(type);
    }

    private Pet buildPet(String type) {
        Pet pet = type.equals(CAT_TYPE) ? new Cat() : new Dog();

        System.out.print("Birth date in format dd.MM.yyyy:  ");
        pet.setBirthDate(validateAndParseDate());

        System.out.print("Name: ");
        pet.setName(InputValidator.validateInputForPattern(PET_NAME_PATTERN,
                "at least one non whitespace character"));

        System.out.print("Sex (male / female): ");
        pet.setSex(InputValidator.validateInputForPattern(SEX_PATTERN,
                "male or m / female or f", InputValidator.Register.LOWER));

        if (type.equals(DOG_TYPE)) {
            System.out.print("Size (XS / S / M / L / XL): ");
            String size = InputValidator.validateInputForPattern(SIZE_PATTERN,
                    "XS / S / M / L / XL", InputValidator.Register.UPPER);
            ((Dog) pet).setSize(Dog.Size.valueOf(size.toUpperCase()));
        }

        System.out.print("What is your view of your pet's health status " +
                "(CRITICAL(C) / SERIOUS(S) / MODERATE(M) / HEALTHY(H)): ");
        parseAndSetHealthStatus(pet);

        return pet;
    }


    private LocalDate validateAndParseDate() {
        String birthDateString = InputValidator.validateInputForPattern(BIRTH_DATE_PATTERN, "dd.MM.yyyy");
        LocalDate birthDate = LocalDate.parse(birthDateString, FORMATTER_BIRTH_DATE);
        while (birthDate.isAfter(LocalDate.now())) {
            System.out.print("You entered future date! Please, enter correct birth date for your pet: ");
            birthDateString = InputValidator.validateInputForPattern(BIRTH_DATE_PATTERN,
                    "dd.MM.yyyy (dd = 01-31, MM = 01-12)");
            birthDate = LocalDate.parse(birthDateString, FORMATTER_BIRTH_DATE);
        }
        return birthDate;
    }


    private void parseAndSetHealthStatus(Pet pet) {
        String healthStatus = InputValidator.validateInputForPattern(HEALTH_PATTERN,
                "CRITICAL or C / SERIOUS or S / MODERATE or M / HEALTHY or H", InputValidator.Register.UPPER);
        switch (healthStatus.toUpperCase()) {
            case "CRITICAL", "C" -> pet.setHealthState(Pet.HealthStatus.CRITICAL);
            case "SERIOUS", "S" -> pet.setHealthState(Pet.HealthStatus.SERIOUS);
            case "MODERATE", "M" -> pet.setHealthState(Pet.HealthStatus.MODERATE);
            case "HEALTHY", "H" -> pet.setHealthState(Pet.HealthStatus.HEALTHY);
        }
    }

    public void sortPets(List<Pet> pets, String sortField, boolean printPets) {
        String field = sortField.trim().toLowerCase();
        switch (field) {
            case "type" -> pets.sort(new Comparator<Pet>() {
                @Override
                public int compare(Pet pet1, Pet pet2) {
                    return pet1.getType().compareTo(pet2.getType());
                }
            });

            case "name" -> pets.sort(new Comparator<Pet>() {
                @Override
                public int compare(Pet pet1, Pet pet2) {
                    return pet1.getName().compareTo(pet2.getName());
                }
            });

            case "sex" -> pets.sort(new Comparator<Pet>() {
                @Override
                public int compare(Pet pet1, Pet pet2) {
                    return pet1.getSex().compareTo(pet2.getSex());
                }
            });

            case "age", "birthdate" -> pets.sort(new Comparator<Pet>() {
                @Override
                public int compare(Pet pet1, Pet pet2) {
                    return pet2.getBirthDate().compareTo(pet1.getBirthDate());
                }
            });

            case "healthstate" -> pets.sort(new Comparator<Pet>() {
                @Override
                public int compare(Pet pet1, Pet pet2) {
                    return pet1.getHealthState().getHealthValue() - pet2.getHealthState().getHealthValue();
                }
            });

            case "size" -> {
                sortPets(pets, "type", false); //first sort pets by type to separate cats and dogs
                pets.sort(new Comparator<Pet>() {
                    @Override
                    public int compare(Pet pet1, Pet pet2) {
                        if ((pet1.getClass() == Dog.class) && (pet2.getClass() == Dog.class)) {
                            return ((Dog) pet1).getSize().getValue() - ((Dog) pet2).getSize().getValue();
                        } else {
                            return 0;
                        }
                    }
                });
            }

            default -> {
                System.out.println("The sorting on '" + sortField + "' is not supported." +
                        "\nThe allowed fields are: 'type', 'name', 'sex', 'age', 'birthdate'," +
                        " 'healthstate' and 'size'(for dogs).");
                return;
            }
        }

        if (printPets) {
            System.out.println("Pets sorted on " + sortField + ":");
            for (Pet pet : pets) {
                System.out.println(pet);
            }
        }
    }
}
