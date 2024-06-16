package main.java.com.magicvet.model;

import java.util.Objects;

public class Dog extends Pet{

    private Size size;

    public Dog() {
        setType(PetType.DOG);
    }

    public Dog(String name, Sex sex, String birthDateString, Size size, HealthStatus healthState){
        super(PetType.DOG, name, sex, birthDateString, healthState);
        this.size = size;
    }

    public String toString(){
        return "Pet {"
                + "type = " + getType()
                + ", name = " + getName()
                + ", sex = " + getSex()
                + ", age = " + getAge()
                + ", size = " + getSize()
                + ", healthState = " + getHealthState()
                + ", ownerName = " + getOwnerName()
                + ", registrationDate = " + getRegistrationDate().format(FORMATTER)
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return Objects.equals(size, dog.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public enum Size {
        UNKNOWN(0),
        XS(1),
        S(2),
        M(3),
        L(4),
        XL(5);

        private final int value;

        Size(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Size fromString(String input) {
            for (Size size : values()) {
                if (size.toString().equals(input.toUpperCase()))  {
                    return size;
                }
            }
            System.out.println("Unable to parse input '" + input + "'. Using default value: " + UNKNOWN);
            return UNKNOWN;
        }
    }
}
