package main.java.com.magicvet.model;

public class Cat extends Pet{

    public Cat(){
        setType(PetType.CAT);
    }
    public Cat (String name, Sex sex, String birthDateString, HealthStatus healthState) {
        super(PetType.CAT, name, sex, birthDateString, healthState);
    }
}
