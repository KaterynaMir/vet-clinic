package main.java.com.magicvet;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static String PASSWORD = "default";
    static Scanner SCANNER = new Scanner(System.in);

    static String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    static String NAME_PATTERN = "^[a-zA-Z-]{3,}$";

    public static void main(String[] args) {
        run();
    }

    static void run() {
        if (auth()) {
            registerNewClient();
        }

    }


    static boolean auth(){
        boolean accepted = false;
        for (int i = 0; i < 3; i++) {
            System.out.print("Password: ");
            String input = SCANNER.nextLine();

            if(PASSWORD.equals(input)) {
                accepted = true;
                break;
            } else {
                System.out.println("Access denied. Please check your password.");
            }
        }

        System.out.println(accepted ? "Welcome to the Magic Vet!" : "Application has been blocked.");
        return accepted;
    }

    static void registerNewClient(){
        System.out.println("Please provide clients details.");
        System.out.print("Email: ");
        String email = SCANNER.nextLine();

        if (isEmailValid(email)) {
           Client client = buildClient(email);
            System.out.println("New client: " + client.firstName + " " + client.lastName + " (" + client.email + ")");
        } else {
            System.out.println("Provided email is invalid.");
        }
    }

    static Client buildClient(String email) {
        Client client = new Client();
        client.email = email;
        client.firstName = checkInputName("First name");
        client.lastName = checkInputName("Last name");;
        return client;
    }

    static String checkInputName(String inputPrompt) {
        System.out.print(inputPrompt + ": ");
        String name = SCANNER.nextLine();
        while (!isNameValid(name)) {
            System.out.println("The " + inputPrompt +" is not valid! Please provide the valid name (only latin letters and hyphen are allowed, the name should be at least 3 letters long): ");
            name = SCANNER.nextLine();
        }
        return name;
    }

    static boolean isNameValid(String name) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}