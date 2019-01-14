package model;


import model.enums.RingsEnum;

import java.io.Serializable;

import static model.enums.MinMaxValues.checkMinMaxValue;

public class ElementRing implements Serializable {


    private String name;
    private int value;
    private String mentalAttributeName;
    private int mentalAttributeValue;
    private String physicalAttributeName;
    private int physicalAttributeValue;

    public ElementRing() {
        name = "DoNotExist";
    }

    public ElementRing(RingsEnum ringsEnum) {
        this.name = ringsEnum.getName();
        this.mentalAttributeName = ringsEnum.getMentalAttribute();
        this.physicalAttributeName = ringsEnum.getPhysicalAttribute();
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getMentalAttributeName() {
        return mentalAttributeName;
    }

    public int getMentalAttributeValue() {
        return mentalAttributeValue;
    }

    public String getPhysicalAttributeName() {
        return physicalAttributeName;
    }

    public int getPhysicalAttributeValue() {
        return physicalAttributeValue;
    }

    public void setMentalAttributeValue(int mentalAttributeValue) {
        this.mentalAttributeValue = checkMinMaxValue(mentalAttributeValue);
        calculateRingValue();
    }

    public void setPhysicalAttributeValue(int physicalAttributeValue) {
        this.physicalAttributeValue = checkMinMaxValue(physicalAttributeValue);
        calculateRingValue();
    }



    private void calculateRingValue() {
        value = Math.min(physicalAttributeValue, mentalAttributeValue);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(name).append("  ").append(value)
                .append("\n")
                .append(mentalAttributeName).append(" ").append(mentalAttributeValue).append("   ")
                .append(physicalAttributeName).append(" ").append(physicalAttributeValue)
                .append("\n").toString();
    }
}
