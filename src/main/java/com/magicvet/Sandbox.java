package main.java.com.magicvet;

import main.java.com.magicvet.comparator.DogAgeComparator;
import main.java.com.magicvet.comparator.DogNameComparator;
import main.java.com.magicvet.comparator.DogSizeComparator;
import main.java.com.magicvet.model.Dog;

import java.util.Arrays;

public class Sandbox {
    public static void main(String[] args) {
        Dog[] dogs = {
                new Dog(Dog.M),
                new Dog(Dog.S),
                new Dog(Dog.XL),
                new Dog(Dog.XL),
                new Dog(Dog.XS),
                new Dog(Dog.S)
        };

        Arrays.sort(dogs, new DogSizeComparator());
        System.out.println("\n\nSorted by size:");
        for (Dog dog : dogs){
            System.out.println(dog.getSize());
        }

        Dog[] dogs2 = {
                new Dog("Olly", "male" , "5 years",Dog.L),
                new Dog("Ben", "male" , "2 years 4 months",Dog.XS),
                new Dog("Rex", "male" , "11 years 6 months",Dog.M),
                new Dog("Mimi", "female" , "6 years 1 month",Dog.S),
                new Dog("Bobby", "male" , "2 months",Dog.L),
                new Dog("Linda", "female" , "3 years",Dog.XS),
                new Dog("Alf", "male" , "4 years 7 months",Dog.XL),
                new Dog("Astra", "female" , "11 months",Dog.M),
        };

        Arrays.sort(dogs2, new DogSizeComparator());
        System.out.println("\n\nSorted by size:");
        for (Dog dog : dogs2){
            System.out.println(dog.getSize());
        }

        Arrays.sort(dogs2, new DogAgeComparator());
        System.out.println("\n\nSorted by age:");
        for (Dog dog : dogs2){
            System.out.println(dog.getAge());
        }

        Arrays.sort(dogs2, new DogNameComparator());
        System.out.println("\n\nSorted by name:");
        for (Dog dog : dogs2){
            System.out.println(dog.getName());
        }
    }
}
