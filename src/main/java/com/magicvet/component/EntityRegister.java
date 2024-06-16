package main.java.com.magicvet.component;

import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Pet;
import main.java.com.magicvet.service.ClientService;
import main.java.com.magicvet.service.InputValidator;
import main.java.com.magicvet.service.PetService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityRegister {

    private final ClientService clientService = new ClientService();
    private final PetService petService = new PetService();

    public void registerClients() {
        List<Client> clients = new ArrayList<>();
        String message = "Do you want to register more clients? (y/n): ";
        do {
            clients.add(addClient());
        } while (verifyRepeating(message));

        Map<Client.Location, List<Client>> clientsByLocation = groupClients(clients);
        printClients(clientsByLocation);
    }

    private void printClients(Map<Client.Location, List<Client>> clientsByLocation) {
        clientsByLocation.forEach((key, value) -> System.out.println("\nLocation: " + key
                + "\nClients (" + value.size() + "):"
                + "\n\t" + value));
    }

    private Map<Client.Location, List<Client>> groupClients(List<Client> clients) {
        List<Client> fromVinnytsia = new ArrayList<>();
        List<Client> fromKharkiv = new ArrayList<>();
        List<Client> fromKyiv = new ArrayList<>();
        List<Client> fromLviv = new ArrayList<>();
        List<Client> fromOdesa = new ArrayList<>();
        List<Client> unknownLocation = new ArrayList<>();

        for (Client client : clients) {
            switch (client.getLocation()) {
                case VINNYTSIA -> fromVinnytsia.add(client);
                case KHARKIV -> fromKharkiv.add(client);
                case KYIV -> fromKyiv.add(client);
                case LVIV -> fromLviv.add(client);
                case ODESA -> fromOdesa.add(client);
                case UNKNOWN -> unknownLocation.add(client);
            }
        }

        Map<Client.Location, List<Client>> clientsByLocation = new HashMap<>();
        clientsByLocation.put(Client.Location.VINNYTSIA, fromVinnytsia);
        clientsByLocation.put(Client.Location.KHARKIV, fromKharkiv);
        clientsByLocation.put(Client.Location.KYIV, fromKyiv);
        clientsByLocation.put(Client.Location.LVIV, fromLviv);
        clientsByLocation.put(Client.Location.ODESA, fromOdesa);
        clientsByLocation.put(Client.Location.UNKNOWN, unknownLocation);

        return clientsByLocation;
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
