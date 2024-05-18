package main.java.com.magicvet.model;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.time.LocalDateTime;

public abstract class Pet {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

    private String type;
    private String sex;
    private String age;
    private String name;
    private String ownerName;
    private HealthStatus healthState;
    private final LocalDateTime registrationDate = LocalDateTime.now();

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
                && Objects.equals(age, pet.age)
                && Objects.equals(name, pet.name)
                && Objects.equals(ownerName, pet.ownerName)
                && Objects.equals(healthState, pet.healthState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, sex, age, name, ownerName, healthState);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = ((sex.equals("m") || sex.equals("male")) ? "male" : "female");
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getRegistrationDate() {
        return registrationDate.format(FORMATTER);
    }

    public enum HealthStatus{
        HEALTHY (4), // the pet is healthy and ready for vaccination/sterilization
        MODERATE (3), // there are some health issues which could be treated at home
                                 // with ambulatory visits to the clinic
        SERIOUS (2),  // the hospitalization is needed
        CRITICAL (1); // reanimation procedures are needed

        private final int healthValue;

        HealthStatus(int healthValue) {
            this.healthValue = healthValue;
        }

        public int getHealthValue() {
            return healthValue;
        }
    }

}
