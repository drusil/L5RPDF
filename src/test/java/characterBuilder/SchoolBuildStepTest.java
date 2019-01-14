package characterBuilder;

import databaseConnectionWithDAO.modelDAO.SchoolData;
import model.CharacterSheet;
import model.Skill;
import model.Skills;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SchoolBuildStepTest {
  SchoolData schoolData;
  CharacterSheet characterSheet;
  SchoolBuildStep schoolBuildStep;

  List<String> startingSkills;
  List<String> startingEquipment;
  HashMap<Integer,String> techniques;

  @Before
  public void before(){
    characterSheet = new CharacterSheet();
    characterSheet.getEquipment().add("Nodachi");
    characterSheet.setHonor(1.0);
    characterSheet.getSkills().addSkill(new Skill("Kenjutsu",2));
    characterSheet.getSkills().addSkill(new Skill("Taktyka",1));

    startingEquipment = new ArrayList<>();
    startingEquipment.add("Katana");
    startingEquipment.add("3 koku");

    techniques = new HashMap<>();
    techniques.put(1,"text");
    techniques.put(2,"text");
    techniques.put(3,"text");
    techniques.put(4,"text");
    techniques.put(5,"text");

    startingSkills = new ArrayList<>();
    startingSkills.add("Kenjutsu");
    startingSkills.add("Kyujutsu");
    startingSkills.add("Taktyka");
    startingSkills.add("Taktyka");
    startingSkills.add("Dowodzenie");
    startingSkills.add("Historia");
    startingSkills.add("Historia");


    schoolData = new SchoolData("Akodo Warmaster",
        3.5,
        "Reflexes",
        startingEquipment,
        techniques,
        startingSkills);

    schoolBuildStep = new SchoolBuildStep(schoolData);
    characterSheet = schoolBuildStep.addData(characterSheet);
  }

  @Test
  public void whenDataAddedThenSchoolAreAdded(){
    assertEquals("Akodo Warmaster",characterSheet.getCharacterInfo().getSchool());
  }

  @Test
  public void whenDataAddedThenTechniquesAreAdded(){
    assertEquals(techniques,characterSheet.getCharacterInfo().getTechniques());
  }

  @Test
  public void whenDataAddedThenSkillsAreAdded() {


     Skills skills = characterSheet.getSkills();
     assertEquals("Kenjutsu 3", skills.getSkillByName("Kenjutsu").toString());
     assertEquals("Kyujutsu 1", skills.getSkillByName("Kyujutsu").toString());
     assertEquals("Dowodzenie 1", skills.getSkillByName("Dowodzenie").toString());
     assertEquals("Historia 2", skills.getSkillByName("Historia").toString());
     assertEquals("Taktyka 3", skills.getSkillByName("Taktyka").toString());
  }

  @Test
  public void whenDataAddedThenEquipmentIsAdded(){
    assertTrue(characterSheet.getEquipment().containsAll(startingEquipment));
  }

  @Test
  public void whenDataAddedThenHonorIsCalculated(){
   assertEquals(4.5,characterSheet.getHonor(),0);
  }

  @Test
  public void whenDataRemovedThenSchoolIsRemoved(){
    characterSheet = schoolBuildStep.removeData(characterSheet);
    assertEquals("",characterSheet.getCharacterInfo().getSchool());
  }

  @Test
  public void whenDataRemovedThenTechniquesAreCleared(){
    characterSheet = schoolBuildStep.removeData(characterSheet);
    assertEquals("",characterSheet.getCharacterInfo().getTechnique(1));
    assertEquals("",characterSheet.getCharacterInfo().getTechnique(2));
    assertEquals("",characterSheet.getCharacterInfo().getTechnique(3));
    assertEquals("",characterSheet.getCharacterInfo().getTechnique(4));
    assertEquals("",characterSheet.getCharacterInfo().getTechnique(5));
  }

  @Test
  public void whenDataRemovedThenSkillsAreCleared(){
    characterSheet = schoolBuildStep.removeData(characterSheet);
    Skills skills = characterSheet.getSkills();
    assertEquals("Kenjutsu 2", skills.getSkillByName("Kenjutsu").toString());;
    assertEquals("Taktyka 1", skills.getSkillByName("Taktyka").toString());
    assertEquals("DoNotExist",skills.getSkillByName("Dowodzenie").getName());
  }

  @Test
  public void whenDataRemovedThenNewHonorValueIsCalculated(){
    characterSheet = schoolBuildStep.removeData(characterSheet);
    assertEquals(1.0,characterSheet.getHonor(),0);
  }

  @Test
  public void whenDataRemovedThenStartingEquipmentIsRemoved(){
    characterSheet = schoolBuildStep.removeData(characterSheet);
    assertTrue(characterSheet.getEquipment().containsAll(Arrays.asList("Nodachi")));
    assertFalse(characterSheet.getEquipment().containsAll(startingEquipment));
  }




}