package databaseConnectionWithDAO.modelDAO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class SchoolData implements Serializable {
    private String schoolName;
    private double startingHonor;
    private String bonusAtributte;
    private List<String> startingEquipment;
    private HashMap<Integer, String> techniques;
    private List<String> startingSkills;

    public SchoolData(String schoolName, double startingHonor, String bonusAtributte, List<String> startingEquipment, HashMap<Integer, String> techniques, List<String> startingSkills) {
        this.schoolName = schoolName;
        this.startingHonor = startingHonor;
        this.bonusAtributte = bonusAtributte;
        this.startingEquipment = startingEquipment;
        this.techniques = techniques;
        this.startingSkills = startingSkills;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public double getStartingHonor() {
        return startingHonor;
    }

    public String getBonusAtributte() {
        return bonusAtributte;
    }

    public List<String> getStartingEquipment() {
        return startingEquipment;
    }

    public HashMap<Integer, String> getTechniques() {
        return techniques;
    }

    public List<String> getStartingSkills() {
        return startingSkills;
    }
}
