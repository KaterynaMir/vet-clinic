package main.java.com.magicvet.component;

import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.service.ClientService;
import main.java.com.magicvet.service.PetService;

public class ApplicationRunner {
    private final ClientService clientService = new ClientService();
    private final PetService petService = new PetService();
    public void run() {
        if (Authenticator.auth()) {
          Client client = clientService.registerNewClient();

          if (client != null) {
              clientService.registerPets(client,petService);
              System.out.println(client);
          }
        }
    }
}
