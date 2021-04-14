package model;

import java.util.Objects;

public class Supplier {
    private String name;
    private String location;

    public Supplier(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }


    @Override
    public String toString() {
        return "Name: " + name + "; Location: " + location;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Supplier supplier = (Supplier) o;
        return Objects.equals(name,supplier.getName()) && Objects.equals(location, supplier.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,location);
    }
}
