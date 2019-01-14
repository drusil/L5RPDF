package model.enums;

public enum RingsEnum {

    EARTH("Earth", "Stamina", "Willpower"),
    AIR("Air", "Reflexes", "Awareness"),
    WATER("Water", "Strength", "Perception"),
    FIRE("Fire", "Agility", "INTELLIGENCE");

    private String name;
    private String physicalAttribute;
    private String mentalAttribute;

    RingsEnum(String name, String phisicalAtributeAtribute, String mentalAtribute) {
        this.name = name;
        this.physicalAttribute = phisicalAtributeAtribute;
        this.mentalAttribute = mentalAtribute;
    }

    public String getName() {
        return name;
    }

    public String getPhysicalAttribute() {
        return physicalAttribute;
    }

    public String getMentalAttribute() {
        return mentalAttribute;
    }


}
