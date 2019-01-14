package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CharacterInfo implements Serializable {
    private String name;
    private String family;
    private String clan;
    private String school;
    private Map<Integer, String> techniques;

    public Map<Integer, String> getTechniques() {
        return techniques;
    }

    public CharacterInfo(String name, String family, String clan, String school) {
        this.name = name;
        this.family = family;
        this.clan = clan;
        this.school = school;
        this.techniques = new HashMap<>();
        createTechniques();
    }

    public CharacterInfo() {
        this("", "", "", "");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getClan() {
        return clan;
    }

    public String getSchool() {
        return school;
    }

    public String getFullName() {
        if (name.equals("")) {
            return family + " noName";
        } else {
            return family + " " + name;
        }
    }

    public String getTechnique(int level) {
        if (level >= 1 || level < 5) {
            return techniques.get(level);
        } else {
            return "";
        }
    }

    public void setTechniques(HashMap techniques) {
        this.techniques = techniques;

    }

    private void createTechniques() {
        techniques.put(1, "");
        techniques.put(2, "");
        techniques.put(3, "");
        techniques.put(4, "");
        techniques.put(5, "");
    }

    @Override
    public String toString() {
        return new StringBuilder("Character Info:")
                .append("\n")
                .append("Name: ").append(getFullName()).append('\n')
                .append("Clan: ").append(clan).append('\n')
                .append("School: ").append(school).append('\n')
                .append("\n").toString();
    }

    public void resetTechniques() {
        techniques = new HashMap<>();
        createTechniques();
    }
}
