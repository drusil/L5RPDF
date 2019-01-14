package model;

public class Advantage {
    private String name;
    private int value;

    public Advantage(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
