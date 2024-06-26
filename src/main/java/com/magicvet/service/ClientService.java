package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;

public class ClientService {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String NAME_PATTERN = "^[a-zA-Z-]{3,}$";

    public Client registerNewClient(){
        System.out.println("Please provide clients details.");
        Client client = buildClient();
        System.out.println("New client: " + client.getFirstName() + " "
                    + client.getLastName() + " (" + client.getEmail() +
                    ") registered on " + client.getClientRegistrationDate().format(Client.FORMATTER_CLIENT_DATE));
        return client;
    }


    private static Client buildClient() {
        Client client = new Client();

        System.out.print("Email: ");
        client.setEmail(InputValidator.validateInputForPattern(EMAIL_PATTERN, "example@email.com"));

        System.out.print("First name: ");
        client.setFirstName(InputValidator.validateInputForPattern(NAME_PATTERN,
                "at least 3 latin letters and/or hyphen"));

        System.out.print("Last name: ");
        client.setLastName(InputValidator.validateInputForPattern(NAME_PATTERN,
                "at least 3 latin letters and/or hyphen"));

        System.out.print("Location ( VINNYTSIA(v) / KHARKIV(kh) / KYIV(k) / LVIV(l) / ODESA(o) ): ");
        String location = Main.SCANNER.nextLine().trim();
        client.setLocation(Client.Location.fromString(location));

        return client;
    }
}
