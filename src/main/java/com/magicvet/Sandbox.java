package main.java.com.magicvet;

import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.model.Pet;
import main.java.com.magicvet.service.PetService;
import java.util.ArrayList;

public class Sandbox {
    public static void main(String[] args) {

        ArrayList<Pet> pets = new ArrayList<>();
        pets.add(new Cat("Neo", Pet.Sex.MALE , "23.10.2017", Pet.HealthStatus.HEALTHY));
        pets.add(new Dog("Olly", Pet.Sex.MALE , "15.05.2019",Dog.Size.L, Pet.HealthStatus.MODERATE));
        pets.add(new Dog("Ben", Pet.Sex.MALE , "01.01.2022",Dog.Size.XS, Pet.HealthStatus.HEALTHY));
        pets.add(new Cat("Nika", Pet.Sex.FEMALE , "31.12.2023", Pet.HealthStatus.MODERATE));
        pets.add(new Dog("Rex", Pet.Sex.MALE, "17.11.2012",Dog.Size.M, Pet.HealthStatus.SERIOUS));
        pets.add(new Dog("Mimi", Pet.Sex.FEMALE , "03.04.2018",Dog.Size.S, Pet.HealthStatus.HEALTHY));
        pets.add(new Dog("Bobby", Pet.Sex.MALE, "08.03.2024",Dog.Size.L, Pet.HealthStatus.CRITICAL));
        pets.add(new Cat("Tom", Pet.Sex.MALE, "28.06.2020", Pet.HealthStatus.SERIOUS));
        pets.add(new Dog("Linda", Pet.Sex.FEMALE , "13.05.2021",Dog.Size.XS, Pet.HealthStatus.MODERATE));
        pets.add(new Dog("Alf", Pet.Sex.MALE, "16.10.2019",Dog.Size.XL, Pet.HealthStatus.SERIOUS));
        pets.add(new Dog("Astra", Pet.Sex.FEMALE , "04.06.2023",Dog.Size.M, Pet.HealthStatus.HEALTHY));

        PetService petService = new PetService();
        petService.sortPets(pets,"name",true);

        System.out.println("\n\n");
        petService.sortPets(pets, "age", true);

        System.out.println("\n\n");
        petService.sortPets(pets, "nickname", true);

        System.out.println("\n\n");
        petService.sortPets(pets, "sex", true);

        System.out.println("\n\n");
        petService.sortPets(pets, "size", true);

        System.out.println("\n\n");
        petService.sortPets(pets, "healthstate", true);



    }
}
