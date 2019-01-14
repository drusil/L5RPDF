package characterBuilder;

import databaseConnectionWithDAO.modelDAO.FamilyData;
import model.CharacterSheet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FamilyBuidStepTest {
  CharacterSheet characterSheet;
  FamilyBuidStep familyBuidStep;
  FamilyData familyData;

  @Before
  public void before(){
    characterSheet = new CharacterSheet();
    familyData = new FamilyData("Akodo","Strength","Lion");
    familyBuidStep = new FamilyBuidStep(familyData);
  }

  @Test
  public void whenAddDataThenFamilyNameUpdated(){
    characterSheet = familyBuidStep.addData(characterSheet);
    assertEquals("Akodo",characterSheet.getCharacterInfo().getFamily());
  }

  @Test
  public void whenAddDataThenAtributeValueUpdated(){
    characterSheet = familyBuidStep.addData(characterSheet);
    assertEquals(1,characterSheet.getRings().getRing("Water").getPhysicalAttributeValue());
  }

  @Test
  public void whenDataRemovedThenFamilyNameIsCleared(){
    characterSheet = familyBuidStep.addData(characterSheet);
    characterSheet = familyBuidStep.removeData(characterSheet);
    assertEquals("", characterSheet.getCharacterInfo().getFamily());
  }

  @Test
  public void whenDataRemovedThenAtributeValueupdated(){
    characterSheet = familyBuidStep.addData(characterSheet);
    characterSheet = familyBuidStep.removeData(characterSheet);
    assertEquals(0, characterSheet.getRings().getRing("Water").getPhysicalAttributeValue());
  }

}