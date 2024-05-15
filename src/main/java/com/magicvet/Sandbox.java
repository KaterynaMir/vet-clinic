package main.java.com.magicvet;

import main.java.com.magicvet.comparator.DogAgeComparator;
import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.model.Pet;

import java.util.Arrays;
import java.util.Comparator;

public class Sandbox {
    public static void main(String[] args) {

        Dog[] dogs = {
                new Dog("Olly", "male" , "5 years",Dog.Size.L, Pet.HealthStatus.MODERATE),
                new Dog("Ben", "male" , "2 years 4 months",Dog.Size.XS, Pet.HealthStatus.HEALTHY),
                new Dog("Rex", "male" , "11 years 6 months",Dog.Size.M, Pet.HealthStatus.SERIOUS),
                new Dog("Mimi", "female" , "6 years 1 month",Dog.Size.S, Pet.HealthStatus.HEALTHY),
                new Dog("Bobby", "male" , "2 months",Dog.Size.L, Pet.HealthStatus.CRITICAL),
                new Dog("Linda", "female" , "3 years",Dog.Size.XS, Pet.HealthStatus.MODERATE),
                new Dog("Alf", "male" , "4 years 7 months",Dog.Size.XL, Pet.HealthStatus.SERIOUS),
                new Dog("Astra", "female" , "11 months",Dog.Size.M, Pet.HealthStatus.HEALTHY),
        };

        Arrays.sort(dogs, new Comparator<Dog>(){
            @Override
            public int compare(Dog dog1, Dog dog2) {
                return dog1.getSize().getValue() - dog2.getSize().getValue();
            }
        });
        System.out.println("\n\nSorted by size:");
        for (Dog dog : dogs){
            System.out.println(dog.getSize());
        }

        Arrays.sort(dogs, new Comparator<Dog>(){
            @Override
            public int compare(Dog dog1, Dog dog2) {
                return dog1.getName().toLowerCase().compareTo(dog2.getName().toLowerCase());
            }
        });
        System.out.println("\n\nSorted by name:");
        for (Dog dog : dogs){
            System.out.println(dog.getName());
        }

        Arrays.sort(dogs, new DogAgeComparator());
        System.out.println("\n\nSorted by age:");
        for (Dog dog : dogs){
            System.out.println(dog.getAge());
        }


        Arrays.sort(dogs, new Comparator<Dog>() {
            @Override
            public int compare(Dog dog1, Dog dog2) {
                return dog1.getHealthState().getHealthValue() - dog2.getHealthState().getHealthValue();
            }
        });
        System.out.println("\n\nSorted by health status:");
        for (Dog dog : dogs){
            System.out.println(dog.getHealthState());
        }

        System.out.println(new Dog("Hugo","male","5 years", Dog.Size.M, Pet.HealthStatus.HEALTHY));
    }
}
