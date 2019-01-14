package characterBuilder;

import model.CharacterSheet;
import model.Rings;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AtributeBuildStepTest {
    CharacterSheet characterSheet;
    AtributeBuildStep strengthBuildStep;
    AtributeBuildStep voidBuildStep;

    @Before
    public void before() {
        characterSheet = new CharacterSheet();
        Rings rings = characterSheet.getRings();
        rings.getRingOfVoid().setValue(2);
        rings.getRingWithAtribute("Strength").setPhysicalAttributeValue(2);
        strengthBuildStep = new AtributeBuildStep("Strength", 2);
        voidBuildStep = new AtributeBuildStep("Void", 3);
        characterSheet = strengthBuildStep.addData(characterSheet);
        characterSheet = voidBuildStep.addData(characterSheet);

    }

    @Test
    public void whenDataIsAddedThenAtributeIsUpdated() {

        assertEquals(4, characterSheet.getRings().getRingWithAtribute("Strength").getPhysicalAttributeValue());
    }

    @Test
    public void whenRingOfVoidDataIsAddedThenAtributeIsUpdated() {

        assertEquals(5, characterSheet.getRings().getRingOfVoid().getValue());
    }

    @Test
    public void whenDataIsRemovedThenAtributeIsUpdated() {
        characterSheet = strengthBuildStep.removeData(characterSheet);
        assertEquals(2, characterSheet.getRings().getRingWithAtribute("Strength").getPhysicalAttributeValue());

    }

    @Test
    public void whenRingOfVoidDataIsRemovedThenAtributeIsUpdated() {
        characterSheet = voidBuildStep.removeData(characterSheet);
        assertEquals(2, characterSheet.getRings().getRingOfVoid().getValue());

    }


}