package main.java.com.magicvet;

import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.model.Pet;

import java.util.Arrays;
import java.util.Comparator;

public class Sandbox {
    public static void main(String[] args) {

        Dog[] dogs = {
                new Dog("Olly", "male" , "15.05.2019",Dog.Size.L, Pet.HealthStatus.MODERATE),
                new Dog("Ben", "male" , "01.01.2022",Dog.Size.XS, Pet.HealthStatus.HEALTHY),
                new Dog("Rex", "male" , "17.11.2012",Dog.Size.M, Pet.HealthStatus.SERIOUS),
                new Dog("Mimi", "female" , "03.04.2018",Dog.Size.S, Pet.HealthStatus.HEALTHY),
                new Dog("Bobby", "male" , "08.03.2024",Dog.Size.L, Pet.HealthStatus.CRITICAL),
                new Dog("Linda", "female" , "13.05.2021",Dog.Size.XS, Pet.HealthStatus.MODERATE),
                new Dog("Alf", "male" , "16.10.2019",Dog.Size.XL, Pet.HealthStatus.SERIOUS),
                new Dog("Astra", "female" , "04.06.2023",Dog.Size.M, Pet.HealthStatus.HEALTHY),
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

        Arrays.sort(dogs, new Comparator<Dog>() {
            @Override
            public int compare(Dog dog1, Dog dog2) {
                return dog2.getBirthDate().compareTo(dog1.getBirthDate());
            }
        });
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

        System.out.println(new Dog("Hugo","male","10.05.2019", Dog.Size.M, Pet.HealthStatus.HEALTHY));
        System.out.println(new Cat());
    }
}
