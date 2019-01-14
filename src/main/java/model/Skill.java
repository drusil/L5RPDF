package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static model.enums.MinMaxValues.checkMinMaxValue;


public class Skill implements Serializable {
    private String name;
    private int value;
    private List<String> specializations;

    public Skill(String name) {
        this.name = name;
        this.value = 0;
        specializations = new ArrayList<>();
    }

    public Skill(String name, int value) {
        this.name = name;
        this.value = value;
        specializations = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public List<String> getSpecializations() {
        return specializations;
    }

    public void setValue(int value) {
        this.value = checkMinMaxValue(value);
    }

    public boolean addSpec(String spec) {
        if (specializations.contains(spec.toLowerCase())) {
            return false;
        } else {
            specializations.add(spec.toLowerCase());
            return true;
        }
    }

    public String getSpecializationsToString() {
        if (specializations.isEmpty()) {
            return "";
        } else {
            return specializations.stream()
                    .collect(Collectors.joining(", "));
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(value);

        if (!specializations.isEmpty()) {
            String spec = specializations.stream()
                    .collect(Collectors.joining(", "));
            sb.append(" [").append(spec).append("]");
        }

        return sb.toString();
    }

    //TODO remove spec, spec cant repeat,
}
