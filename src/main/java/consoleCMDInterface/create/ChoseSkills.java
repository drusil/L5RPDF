package consoleCMDInterface.create;

import characterBuilder.CharacterBuilder;
import characterBuilder.SkillBuildStep;
import consoleCMDInterface.HelperMetods;
import model.Skill;
import model.Skills;

import java.util.Scanner;

public class ChoseSkills extends CreationStage {
    Skills skills;

    public ChoseSkills(CharacterBuilder characterBuilder, Scanner scanner, String name) {
        super(characterBuilder, scanner, name);
    }

    @Override
    public void run() {
        skills = characterBuilder.getCharacterSheet().getSkills();
        int skillListSize;
        int newSkillChoice;
        int delateSkillChoice;
        int exitChoice;
        int choice = 0;
        do {
            newSkillChoice = skills.getListOfSkills().size() + 1;
            delateSkillChoice = skills.getListOfSkills().size() + 2;
            exitChoice = skills.getListOfSkills().size() + 3;
            skillListSize = skills.getListOfSkills().size();
            printMenu();
            choice = HelperMetods.scanIntValue(scanner, 1, exitChoice);
            if (choice < skillListSize) {
                editSkill(choice - 1);
            } else if (choice == newSkillChoice) {
                createNewSkill();
            } else if (choice == delateSkillChoice) {
                deleteSkill();
            }

        } while (choice != exitChoice);

    }

    private void deleteSkill() {
        //TODO Delate Skill options

    }

    private void createNewSkill() {
        //TODO Get list of skill to choice
        System.out.println("Put new skill name");
        String name = HelperMetods.scanStringValue(scanner);
        System.out.println("Put new skill value");
        int value = HelperMetods.scanIntValue(scanner);
        Skill skill = new Skill(name, value);
        SkillBuildStep skillBuildStep = new SkillBuildStep(skill);
        characterBuilder.addSkillBuildStep(skillBuildStep);

    }

    private void editSkill(int choice) {

        //TODO Add specializations
        Skill skill = skills.getListOfSkills().get(choice);
        System.out.println(skill);
        int newValue = HelperMetods.scanIntValue(scanner);
        SkillBuildStep skillBuildStep = new SkillBuildStep(new Skill(skill.getName(), newValue));
        characterBuilder.addSkillBuildStep(skillBuildStep);
    }

    private void printMenu() {
        skills.getListOfSkills().stream()
                .forEachOrdered(skill -> System.out.printf("%d) %s %n", skills.getListOfSkills().indexOf(skill) + 1, skill.toString()));
        System.out.printf("%d) Add new skill%n", skills.getListOfSkills().size() + 1);
        System.out.printf("%d) Delete skill skill%n", skills.getListOfSkills().size() + 2);
        System.out.printf("%d) Exit%n", skills.getListOfSkills().size() + 3);
    }
}
