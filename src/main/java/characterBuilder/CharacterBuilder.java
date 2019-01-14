package characterBuilder;

import model.CharacterSheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CharacterBuilder implements Serializable {
    private int id;

    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        name = characterSheet.getCharacterInfo().getFullName();
        return name;
    }

    CharacterSheet characterSheet;

    private ClanBuildStep clanBuildStep;
    private FamilyBuidStep familyBuidStep;
    private SchoolBuildStep schoolBuildStep;
    private List<SkillBuildStep> skillsBuildSteps;
    private List<AtributeBuildStep> atributeBuildSteps;


    public CharacterBuilder() {
        characterSheet = new CharacterSheet();
        skillsBuildSteps = new ArrayList<>(30);
        atributeBuildSteps = new ArrayList<>(9);
        id = -1;
    }

    public void setSchoolBuildStep(SchoolBuildStep schoolBuildStep) {
        if (this.schoolBuildStep != null) schoolBuildStep.removeData(characterSheet);
        this.schoolBuildStep = schoolBuildStep;
        characterSheet = schoolBuildStep.addData(characterSheet);
    }

    public void setClanBuildStep(ClanBuildStep clanBuildStep) {
        if (this.clanBuildStep != null) clanBuildStep.removeData(characterSheet);
        this.clanBuildStep = clanBuildStep;
        characterSheet = clanBuildStep.addData(characterSheet);
    }

    public void setFamilyBuidStep(FamilyBuidStep familyBuidStep) {
        if (this.familyBuidStep != null) familyBuidStep.removeData(characterSheet);
        this.familyBuidStep = familyBuidStep;
        characterSheet = familyBuidStep.addData(characterSheet);
    }

    public void addSkillBuildStep(SkillBuildStep skillBuildStep) {
        String currentBuildStepName = skillBuildStep.getSkillName();
        removeSkillStep(currentBuildStepName);

        skillsBuildSteps.add(skillBuildStep);
        skillBuildStep.addData(characterSheet);
    }

    public void removeSkillStep(String name) {
        if (isSkillStepExist(name)) {

            SkillBuildStep existing = skillsBuildSteps.stream()
                    .filter(s -> s.getSkillName().equalsIgnoreCase(name))
                    .findFirst().get();
            existing.removeData(characterSheet);
            skillsBuildSteps.remove(existing);
        }
    }

    public void addAtributeStep(AtributeBuildStep atributeBuildStep) {
        String atributeName = atributeBuildStep.getAtributeName();

        removeAtributeStep(atributeName);

        atributeBuildStep.addData(characterSheet);
        atributeBuildSteps.add(atributeBuildStep);
    }

    public void removeAtributeStep(String name) {
        if (isAtributeBuildStepExist(name)) {
            AtributeBuildStep existing = atributeBuildSteps.stream()
                    .filter(abs -> abs.getAtributeName().equalsIgnoreCase(name))
                    .findFirst()
                    .get();
            existing.removeData(characterSheet);
            atributeBuildSteps.remove(existing);
        }
    }


    private boolean isAtributeBuildStepExist(String name) {
        return atributeBuildSteps.stream()
                .anyMatch(a -> a.getAtributeName().equalsIgnoreCase(name));
    }

    private boolean isSkillStepExist(String name) {
        return skillsBuildSteps.stream()
                .anyMatch(s -> s.getSkillName().equalsIgnoreCase(name));
    }


    public CharacterSheet getCharacterSheet() {
        return characterSheet;
    }


}
