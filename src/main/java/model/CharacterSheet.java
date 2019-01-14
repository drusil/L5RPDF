package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CharacterSheet implements Serializable {

    private CharacterInfo characterInfo;
    private Rings rings;
    private Skills skills;
    private int insight = 0;
    private double honor = 0;
    private List<String> equipment;

    public int getInsight() {
        calculateInsight();
        return insight;
    }

    public CharacterSheet() {
        this.characterInfo = new CharacterInfo();
        this.rings = new Rings();
        this.skills = new Skills();
        this.equipment = new ArrayList<>();
    }

    public void setCharacterInfo(CharacterInfo characterInfo) {
        this.characterInfo = characterInfo;
    }

    public CharacterInfo getCharacterInfo() {
        return characterInfo;
    }

    public Rings getRings() {
        return rings;
    }

    public Skills getSkills() {
        return skills;
    }

    public void calculateInsight() {
        insight = (rings.getElementRings()
                .stream().mapToInt(ElementRing::getValue)
                .sum()
                + rings.getRingOfVoid().getValue()) * 10
                + skills.getListOfSkills().stream()
                .mapToInt(Skill::getValue)
                .sum();
    }

    @Override
    public String toString() {
        return new StringBuilder("CharacterSheet: \n")
                .append(characterInfo)
                .append("\n")
                .append(rings)
                .append("\n")
                .append(skills)
                .append("\n").toString();
    }

    public void setHonor(double honor) {
        this.honor = honor;
    }

    public void setEquipment(List<String> startingEquipment) {
        this.equipment = startingEquipment;
    }

    public double getHonor() {
        return honor;
    }

    public List<String> getEquipment() {
        return equipment;
    }
}
