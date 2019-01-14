package characterBuilder;

import model.CharacterSheet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClanBuildStepTest {

  private CharacterSheet characterSheet;
  private ClanBuildStep clanBuildStep;

  @Before
  public void before(){
    characterSheet = new CharacterSheet();
     clanBuildStep = new ClanBuildStep("Lion");
  }

  @Test
  public void whenDataIsAddedThenCharacterSheetIsUpdated(){
    assertEquals("",characterSheet.getCharacterInfo().getClan());
    characterSheet = clanBuildStep.addData(characterSheet);
    assertEquals("Lion",characterSheet.getCharacterInfo().getClan());
  }

  @Test
  public void whenDataIsRemovedThenCharacterSheetIsUpdated(){
    characterSheet = clanBuildStep.addData(characterSheet);
    assertEquals("Lion",characterSheet.getCharacterInfo().getClan());
    characterSheet = clanBuildStep.removeData(characterSheet);
    assertEquals("",characterSheet.getCharacterInfo().getClan());
  }


}