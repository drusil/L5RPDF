package characterBuilder;

import databaseConnectionWithDAO.modelDAO.FamilyData;
import model.Skill;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterBuilderTest {


  CharacterBuilder characterBuilder;

  @Before
  public void before() {
    characterBuilder = new CharacterBuilder();

  }

  @Test
  public void whenSetClanBuildStepThenClanSetOn() {
    ClanBuildStep cbs = new ClanBuildStep("Lion");
    characterBuilder.setClanBuildStep(cbs);
    assertEquals("Lion", characterBuilder.getCharacterSheet().getCharacterInfo().getClan());
    cbs = new ClanBuildStep("Crab");
    characterBuilder.setClanBuildStep(cbs);
    assertEquals("Crab", characterBuilder.getCharacterSheet().getCharacterInfo().getClan());

  }

  @Test
  public void setFamilyBuidStep() {
    FamilyBuidStep fbs = new FamilyBuidStep(new FamilyData("Akodo", "Strength", "Lion"));
    characterBuilder.setFamilyBuidStep(fbs);
    assertEquals("Akodo", characterBuilder.getCharacterSheet().getCharacterInfo().getFamily());
    fbs = new FamilyBuidStep((new FamilyData("Ikoma", "Willpower", "Lion")));
    characterBuilder.setFamilyBuidStep(fbs);
    assertEquals("Ikoma", characterBuilder.getCharacterSheet().getCharacterInfo().getFamily());

  }

  @Test
  public void addSkilBuildStep() {
    SkillBuildStep sbs = new SkillBuildStep(new Skill("Kenjutsu", 3));
    characterBuilder.addSkillBuildStep(sbs);
    assertEquals("Kenjutsu", characterBuilder.getCharacterSheet().getSkills().getSkillByName("kenjutsu").getName());
    assertEquals(3, characterBuilder.getCharacterSheet().getSkills().getSkillByName("kenjutsu").getValue());
    sbs = new SkillBuildStep(new Skill("Kenjutsu", 2));
    characterBuilder.addSkillBuildStep(sbs);
    assertEquals("Kenjutsu", characterBuilder.getCharacterSheet().getSkills().getSkillByName("kenjutsu").getName());
    assertEquals(2, characterBuilder.getCharacterSheet().getSkills().getSkillByName("kenjutsu").getValue());

  }

  @Test
  public void removeSkillStep() {
    SkillBuildStep sbs = new SkillBuildStep(new Skill("Kenjutsu", 3));
    characterBuilder.addSkillBuildStep(sbs);
    assertEquals("Kenjutsu", characterBuilder.getCharacterSheet().getSkills().getSkillByName("kenjutsu").getName());
    assertEquals(3, characterBuilder.getCharacterSheet().getSkills().getSkillByName("kenjutsu").getValue());

  }

  @Test
  public void addAtributeStep() {
  }

  @Test
  public void removeAtributeStep() {
  }



}