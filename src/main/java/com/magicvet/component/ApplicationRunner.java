package main.java.com.magicvet.component;

import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.service.ClientService;
import main.java.com.magicvet.service.PetService;

public class ApplicationRunner {
    private ClientService clientService = new ClientService();
    private PetService petService = new PetService();
    public void run() {
        if (Authenticator.auth()) {
          Client client = clientService.registerNewClient();

          if (client != null) {
              clientService.addPet(client, petService);
          }
        }
    }
}
