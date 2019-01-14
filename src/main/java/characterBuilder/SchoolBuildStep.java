package characterBuilder;

import databaseConnectionWithDAO.modelDAO.SchoolData;
import model.CharacterInfo;
import model.CharacterSheet;
import model.Skill;

import java.io.Serializable;
import java.util.List;

public class SchoolBuildStep implements BuildStep, Serializable {
    SchoolData schoolData;

    public SchoolBuildStep(SchoolData schoolData) {
        this.schoolData = schoolData;
    }

    @Override
    public CharacterSheet addData(CharacterSheet characterSheet) {
        CharacterInfo characterInfo = characterSheet.getCharacterInfo();
        characterInfo.setSchool(schoolData.getSchoolName());
        characterInfo.setTechniques(schoolData.getTechniques());

        double oldHonorValue = characterSheet.getHonor();
        characterSheet.setHonor(schoolData.getStartingHonor() + oldHonorValue);

        addEquipment(schoolData.getStartingEquipment(), characterSheet);

        List<String> startingSkills = schoolData.getStartingSkills();

        startingSkills.stream()
                .forEach(skill -> editSkillByName(skill, characterSheet, 1));

        HelperMethods.editAtributeByName(schoolData.getBonusAtributte(), characterSheet, 1);

        return characterSheet;
    }

    @Override
    public CharacterSheet removeData(CharacterSheet characterSheet) {
        CharacterInfo characterInfo = characterSheet.getCharacterInfo();
        characterInfo.setSchool("");
        characterInfo.resetTechniques();

        double newHonorValue = characterSheet.getHonor() - schoolData.getStartingHonor();
        characterSheet.setHonor(newHonorValue);

        removeEquipment(schoolData.getStartingEquipment(), characterSheet);

        List<String> startingSkills = schoolData.getStartingSkills();
        startingSkills.stream()
                .forEach(skill -> editSkillByName(skill, characterSheet, -1));
        HelperMethods.editAtributeByName(schoolData.getBonusAtributte(), characterSheet, -1);
        return characterSheet;
    }


    private void editSkillByName(String name, CharacterSheet characterSheet, int value) {
        Skill skill = characterSheet.getSkills().getSkillByName(name);

        //If Skill do not exist create one
        if (skill.getName().equals("DoNotExist")) {
            characterSheet.getSkills().addSkill(new Skill(name, 0));

        }
        skill = characterSheet.getSkills().getSkillByName(name);
        int newNalue = skill.getValue() + value;
        characterSheet.getSkills().getSkillByName(name).setValue(newNalue);


        // If skill vale go to 0 then delete skill
        if (skill.getValue() <= 0) {
            characterSheet.getSkills().removeSkillByName(name);
        }
    }

    private void addEquipment(List<String> equipment, CharacterSheet characterSheet) {
        for (String eq : equipment) {
            characterSheet.getEquipment().add(eq);
        }
    }

    private void removeEquipment(List<String> equipemnt, CharacterSheet characterSheet) {
        for (String eq : equipemnt) {
            if (characterSheet.getEquipment().contains(eq)) {
                characterSheet.getEquipment().remove(eq);
            }

        }
    }
}
