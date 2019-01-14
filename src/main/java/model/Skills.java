package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Skills implements Serializable {
    private List<Skill> skillList;

    public Skills() {
        this.skillList = new ArrayList<>();
    }

    public List<Skill> getListOfSkills() {
        return skillList;
    }

    public boolean addSkill(Skill skill) {

        if (containsSkill(skill.getName())) {
            return false;
        } else {
            skillList.add(skill);
            return true;
        }
    }

    public boolean containsSkill(String name) {
        return skillList.stream()
                .anyMatch(s -> s.getName().equalsIgnoreCase(name));
    }

    public Skill getSkillByName(String skillName) {
        return skillList.stream()
                .filter(s -> s.getName().equalsIgnoreCase(skillName))
                .findFirst().orElse(new Skill("DoNotExist"));
    }

    public void removeSkillByName(String name) {
        if (containsSkill(name))
            skillList.remove(skillList.indexOf(getSkillByName(name)));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Skills \n");

        if (!skillList.isEmpty()) {
            skillList.forEach(sb::append);
        }
        return sb.toString();
    }


}
