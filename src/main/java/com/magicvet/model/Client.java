package main.java.com.magicvet.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {

    public static final DateTimeFormatter FORMATTER_CLIENT_DATE = DateTimeFormatter.ofPattern("yyyy/MMM/dd HH:mm");
    private String firstName;
    private String lastName;
    private String email;
    private List<Pet> pets = new ArrayList<>();
    private final LocalDateTime clientRegistrationDate = LocalDateTime.now();
    private Location location;

    @Override
    public String toString() {
        StringBuilder petsString= new StringBuilder();
        pets.forEach(p -> petsString.append("\n\t").append(p));

        return "Client {"
                + "\n\tfirstName = " + firstName
                + ", lastName = " + lastName
                + ", email = " + email
                + ", location = " + location
                + ", clientRegistrationDate = " + clientRegistrationDate.format(FORMATTER_CLIENT_DATE)
                + ",\n\tpets = " + petsString
                + "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName)
                && Objects.equals(lastName, client.lastName)
                && Objects.equals(email, client.email)
                && Objects.equals(pets, client.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, pets);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public LocalDateTime getClientRegistrationDate() {
        return clientRegistrationDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public enum Location {
        VINNYTSIA("v"),
        KHARKIV("kh"),
        KYIV("k"),
        LVIV("l"),
        ODESA("o"),
        UNKNOWN("unk");

        private final String shortForm;

        Location(String shortForm) {
            this.shortForm = shortForm;
        }

        public String getShortForm() {
            return shortForm;
        }

        public static Location fromString(String input) {
            for (Location location : values()) {
                if (location.toString().equals(input.toUpperCase()) ||
                        location.getShortForm().equals(input.toLowerCase())) {
                    return location;
                }
            }
            System.out.println("Unable to parse input '" + input + "'. Using default value: " + UNKNOWN);
            return UNKNOWN;
        }
    }
}
