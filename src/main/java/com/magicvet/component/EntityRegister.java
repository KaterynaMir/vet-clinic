package main.java.com.magicvet.component;

import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Pet;
import main.java.com.magicvet.service.ClientService;
import main.java.com.magicvet.service.InputValidator;
import main.java.com.magicvet.service.PetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityRegister {

    private final ClientService clientService = new ClientService();
    private final PetService petService = new PetService();

    public void registerClients() {
        List<Client> clients = new ArrayList<>();
        String message = "Do you want to register more clients? (y/n): ";
        do {
            clients.add(addClient());
        } while (verifyRepeating(message));

        Map<Client.Location, List<Client>> clientsByLocation = clients.stream()
                .collect(Collectors.groupingBy(Client::getLocation));
        printClients(clientsByLocation);
    }

    private void printClients(Map<Client.Location, List<Client>> clientsByLocation) {
        clientsByLocation.forEach((key, value) -> System.out.println("\nLocation: " + key
                + "\nClients (" + value.size() + "):"
                + "\n\t" + value));
    }

    private Client addClient() {
        Client client = clientService.registerNewClient();
        registerPets(client);
        System.out.println(client);
        return client;
    }

    public void registerPets(Client client) {
        String message = "Do you want to add more pets for the current client? (y/n): ";
        do {
            addPet(client);
        } while (verifyRepeating(message));
    }

    public void addPet(Client client) {
        System.out.println("Adding a new pet.");
        Pet pet = petService.registerNewPet();
        client.addPet(pet);
        pet.setOwnerName(client.getFirstName() + " " + client.getLastName());
        System.out.println("Pet has been added.");
    }

    private boolean verifyRepeating(String message) {
        System.out.print(message);
        String answer = InputValidator.validateInputForPattern("(y|n)",
                "y for 'yes' or n for 'no'", InputValidator.Register.LOWER);
        return answer.equals("y");
    }
}
