package characterBuilder;

import model.CharacterSheet;
import model.Skill;

import java.io.Serializable;

public class SkillBuildStep implements BuildStep, Serializable {

    Skill skill;

    public String getSkillName() {
        return skill.getName();
    }


    public SkillBuildStep(Skill skill) {
        this.skill = skill;
    }

    @Override
    public CharacterSheet addData(CharacterSheet characterSheet) {
        if (!characterSheet.getSkills().containsSkill(skill.getName())) {
            characterSheet.getSkills().addSkill(skill);
        } else {
            Skill existingSkill = characterSheet.getSkills().getSkillByName(skill.getName());
            existingSkill.setValue(existingSkill.getValue() + skill.getValue());
            skill.getSpecializations().stream()
                    .forEach(existingSkill::addSpec);
        }
        return characterSheet;
    }

    @Override
    public CharacterSheet removeData(CharacterSheet characterSheet) {
        String updatedSkillName = skill.getName();

        if (characterSheet.getSkills().containsSkill(updatedSkillName)) {
            if (characterSheet.getSkills().getSkillByName(updatedSkillName).equals(skill)) {
                characterSheet.getSkills().removeSkillByName(updatedSkillName);
            } else {
                int currentValue = characterSheet.getSkills().getSkillByName(updatedSkillName).getValue();
                int newValue = currentValue - skill.getValue();
                characterSheet.getSkills().getSkillByName(updatedSkillName).setValue(newValue);
                characterSheet.getSkills().getSkillByName(updatedSkillName).getSpecializations().clear();
            }
        }

        return characterSheet;
    }


}
