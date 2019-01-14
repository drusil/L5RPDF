package characterBuilder;

import model.CharacterSheet;
import model.Skill;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkillBuildStepTest {

  CharacterSheet characterSheet;
  Skill skill;
  Skill defaultSkill;
  SkillBuildStep skillBuildStep;

  @Before
  public void before(){
    characterSheet = new CharacterSheet();

    skill = new Skill("Kenjutsu");
    skill.setValue(3);
    skill.addSpec("katana");
    skill.addSpec("nodachi");

    defaultSkill = new Skill("kenjutsu");
    defaultSkill.setValue(1);


    skillBuildStep = new SkillBuildStep(skill);
  }

  @Test
  public void whenAddedNotPresentSkillThenCharacterSheetIsCorrectlyUpdated(){
    characterSheet = skillBuildStep.addData(characterSheet);
    assertEquals("kenjutsu",characterSheet.getSkills().getSkillByName("kenjutsu").getName().toLowerCase());
    assertEquals(3,characterSheet.getSkills().getSkillByName("kenjutsu").getValue());
    assertEquals("katana, nodachi",characterSheet.getSkills().getSkillByName("kenjutsu").getSpecializationsToString());

  }

  @Test
  public void whenSkillAddedAmdPresentThenSkillIsCombinedAndCorrectlyUpdated(){
    characterSheet.getSkills().addSkill(defaultSkill);
    characterSheet = skillBuildStep.addData(characterSheet);
    assertEquals("kenjutsu",characterSheet.getSkills().getSkillByName("kenjutsu").getName().toLowerCase());
    assertEquals(4,characterSheet.getSkills().getSkillByName("kenjutsu").getValue());
    assertEquals("katana, nodachi",characterSheet.getSkills().getSkillByName("kenjutsu").getSpecializationsToString());
  }

  @Test
  public void whenRemovedAndDoNotPresentEarlierThenSkillRemovedFromCharacterSheet(){
    characterSheet = skillBuildStep.addData(characterSheet);
    characterSheet= skillBuildStep.removeData(characterSheet);
    assertEquals("DoNotExist", characterSheet.getSkills().getSkillByName("kenjutsu").getName());
  }

  @Test
  public void whenRemovedAndDoPresentEarlierThenSkillUpdatedInCharacterSheet(){
    characterSheet.getSkills().addSkill(defaultSkill);
    characterSheet = skillBuildStep.addData(characterSheet);
    characterSheet= skillBuildStep.removeData(characterSheet);
    assertEquals("kenjutsu", characterSheet.getSkills().getSkillByName("kenjutsu").getName());
    assertEquals(1, characterSheet.getSkills().getSkillByName("kenjutsu").getValue());
    assertTrue(characterSheet.getSkills().getSkillByName("kenjutsu").getSpecializations().isEmpty());
  }

}