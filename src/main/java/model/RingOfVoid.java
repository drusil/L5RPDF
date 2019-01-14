package model;

import java.io.Serializable;

import static model.enums.MinMaxValues.checkMinMaxValue;


public class RingOfVoid implements Serializable {
    private String name;
    private int value;

    public RingOfVoid() {
        this.name = "Void";
        value = 0;
    }

    public void setValue(int value) {
        this.value = checkMinMaxValue(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s %d%s", name, value, System.lineSeparator());
    }
}
