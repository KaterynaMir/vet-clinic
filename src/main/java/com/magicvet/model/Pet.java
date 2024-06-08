package main.java.com.magicvet.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.time.LocalDateTime;

public abstract class Pet {

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
    public static final DateTimeFormatter FORMATTER_BIRTH_DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private PetType type;
    private Sex sex;
    private LocalDate birthDate;
    private String age;
    private String name;
    private String ownerName;
    private HealthStatus healthState;
    private final LocalDateTime registrationDate = LocalDateTime.now();


    public  Pet(){}
    public Pet(PetType type, String name, Sex sex, String birthDateString, HealthStatus healthState){
        this.type = type;
        this.name = name;
        this.sex = sex;
        setBirthDate(LocalDate.parse(birthDateString,FORMATTER_BIRTH_DATE));
        this.healthState = healthState;
    }

    @Override
    public String toString(){
        return "Pet {"
                + "type = " + type
                + ", name = " + name
                + ", sex = " + sex
                + ", age = " + age
                + ", healthState = " + healthState
                + ", ownerName = " + ownerName
                + ", registrationDate = " + registrationDate.format(FORMATTER)
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(type, pet.type)
                && Objects.equals(sex, pet.sex)
                && Objects.equals(birthDate, pet.birthDate)
                && Objects.equals(name, pet.name)
                && Objects.equals(ownerName, pet.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, sex, birthDate, name, ownerName);
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = (sex.equals("m") || sex.equals("male")) ? Sex.MALE : Sex.FEMALE;
    }

    public LocalDate getBirthDate() { return birthDate; }

    public void setBirthDate (LocalDate birthDate) {
        this.birthDate = birthDate;
        Period agePeriod = Period.between(birthDate,LocalDate.now());
        age = String.format("%d year(s) %d month(s)",agePeriod.getYears(),agePeriod.getMonths());
    }
    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public HealthStatus getHealthState() { return healthState; }

    public void setHealthState(HealthStatus healthState) { this.healthState = healthState; }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public enum PetType {
        CAT,
        DOG
    }

    public enum Sex {
        MALE("m"),
        FEMALE("f");

        private final String shortForm;

        Sex(String shortForm) {
            this.shortForm = shortForm;
        }

        public String getShortForm() {
            return shortForm;
        }
    }

    public enum HealthStatus {
        HEALTHY (4), // the pet is healthy and ready for vaccination/sterilization
        MODERATE (3), // there are some health issues which could be treated at home
                                 // with ambulatory visits to the clinic
        SERIOUS (2),  // the hospitalization is needed
        CRITICAL (1), // reanimation procedures are needed
        UNKNOWN (0); // illegal input

        private final int healthValue;

        HealthStatus(int healthValue) {
            this.healthValue = healthValue;
        }

        public int getHealthValue() {
            return healthValue;
        }

        public static HealthStatus fromString(String healthStatus) {
            switch (healthStatus.toUpperCase()) {
                case "CRITICAL", "C" -> {return CRITICAL;}
                case "SERIOUS", "S" -> {return SERIOUS;}
                case "MODERATE", "M" -> {return MODERATE;}
                case "HEALTHY", "H" -> {return HEALTHY;}
                default -> {
                    System.out.println("Unable to parse input '" + healthStatus +
                            "'. Using default value: " + UNKNOWN);
                    return UNKNOWN;}
            }

        }
    }

}
