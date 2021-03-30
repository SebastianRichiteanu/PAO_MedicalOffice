package model;

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
}
