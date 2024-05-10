package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Pet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientService {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String NAME_PATTERN = "^[a-zA-Z-]{3,}$";

    public Client registerNewClient(){
        Client client = null;

        System.out.println("Please provide clients details.");
        System.out.print("Email: ");
        String email = Main.SCANNER.nextLine();

        if (isEmailValid(email)) {
            client = buildClient(email);
            System.out.println("New client: " + client.getFirstName() + " "
                    + client.getLastName() + " (" + client.getEmail() + ")");
        } else {
            System.out.println("Provided email is invalid.");
        }

        return client;
    }

    public void addPet(Client client, PetService petService) {
        System.out.print("Do you want to add a new pet now? (y/n): ");
        String answerAddPet = Main.SCANNER.nextLine();
        while (true){
            if (answerAddPet.toLowerCase().trim().equals("y")) {
                System.out.println("Adding a new pet.");
                Pet pet = petService.registerNewPet();
                if (pet != null){
                    client.setPet(pet);
                    pet.setOwnerName(client.getFirstName() + " " + client.getLastName());
                    System.out.println("Pet has been added.");
                }
                break;
            } else if (answerAddPet.toLowerCase().trim().equals("n")){
                System.out.println("You can register your pet later. Have a nice day!");
                break;
            } else {
                System.out.print("The answer " + answerAddPet +" is not valid! " +
                        "Please, enter y for 'yes' or n for 'no': ");
                answerAddPet = Main.SCANNER.nextLine();
            }
        }
    }

    private static Client buildClient(String email) {
        Client client = new Client();
        client.setEmail(email);
        client.setFirstName(checkInputName("First name"));
        client.setLastName(checkInputName("Last name"));
        return client;
    }

    private static String checkInputName(String inputPrompt) {
        System.out.print(inputPrompt + ": ");
        String name = Main.SCANNER.nextLine();
        while (!isNameValid(name)) {
            System.out.println("The " + inputPrompt +" is not valid! Please provide the valid name (only latin letters and hyphen are allowed, the name should be at least 3 letters long): ");
            System.out.print(inputPrompt + ": ");
            name = Main.SCANNER.nextLine();
        }
        return name;
    }

    private static boolean isNameValid(String name) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
