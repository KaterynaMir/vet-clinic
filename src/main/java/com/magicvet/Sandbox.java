package main.java.com.magicvet;

import main.java.com.magicvet.comparator.DogAgeComparator;
import main.java.com.magicvet.model.Dog;

import java.util.Arrays;
import java.util.Comparator;

public class Sandbox {
    public static void main(String[] args) {

        Dog[] dogs = {
                new Dog("Olly", "male" , "5 years",Dog.Size.L),
                new Dog("Ben", "male" , "2 years 4 months",Dog.Size.XS),
                new Dog("Rex", "male" , "11 years 6 months",Dog.Size.M),
                new Dog("Mimi", "female" , "6 years 1 month",Dog.Size.S),
                new Dog("Bobby", "male" , "2 months",Dog.Size.L),
                new Dog("Linda", "female" , "3 years",Dog.Size.XS),
                new Dog("Alf", "male" , "4 years 7 months",Dog.Size.XL),
                new Dog("Astra", "female" , "11 months",Dog.Size.M),
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
    }
}
