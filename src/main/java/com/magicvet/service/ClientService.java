package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Pet;


public class ClientService {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String NAME_PATTERN = "^[a-zA-Z-]{3,}$";

    public Client registerNewClient(){
        Client client = null;

        System.out.println("Please provide clients details.");
        client = buildClient();
        System.out.println("New client: " + client.getFirstName() + " "
                    + client.getLastName() + " (" + client.getEmail() + ")");
        return client;
    }

    public void addPet(Client client, PetService petService) {
        System.out.print("Do you want to add a new pet now? (y/n): ");
        String answerAddPet = InputValidator.validateInputForPattern(Main.SCANNER.nextLine(),"(y|n)",
                "y for 'yes' or n for 'no'", InputValidator.Register.LOWER);
        if (answerAddPet.equals("y")) {
                System.out.println("Adding a new pet.");
                Pet pet = petService.registerNewPet();
                client.setPet(pet);
                pet.setOwnerName(client.getFirstName() + " " + client.getLastName());
                System.out.println("Pet has been added.");
            } else {
                System.out.println("You can register your pet later. Have a nice day!");
            }
        }

    private static Client buildClient() {
        Client client = new Client();
        System.out.print("Email: ");
        client.setEmail(InputValidator.validateInputForPattern(Main.SCANNER.nextLine(), EMAIL_PATTERN,
                "example@email.com", InputValidator.Register.IGNORE));
        System.out.print("First name: ");
        client.setFirstName(InputValidator.validateInputForPattern(Main.SCANNER.nextLine(), NAME_PATTERN,
                "at least 3 latin letters and/or hyphen", InputValidator.Register.IGNORE));
        System.out.print("Last name: ");
        client.setLastName(InputValidator.validateInputForPattern(Main.SCANNER.nextLine(), NAME_PATTERN,
                "at least 3 latin letters and/or hyphen", InputValidator.Register.IGNORE));
        return client;
    }
}
