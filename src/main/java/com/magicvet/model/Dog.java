package main.java.com.magicvet.model;

import java.util.Objects;

public class Dog extends Pet{
    private String size;

    public String toString(){
        return "Pet {"
                + "type = " + getType()
                + ", name = " + getName()
                + ", sex = " + getSex()
                + ", age = " + getAge()
                + ", size = " + size
                + ", ownerName = " + getOwnerName()
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
