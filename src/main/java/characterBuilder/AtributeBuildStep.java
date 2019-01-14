package characterBuilder;

import model.CharacterSheet;

import java.io.Serializable;

public class AtributeBuildStep implements BuildStep, Serializable {
    private String atributeName;
    private int value;

    public AtributeBuildStep(String atributeName, int value) {
        this.atributeName = atributeName;
        this.value = value;
    }

    @Override
    public CharacterSheet addData(CharacterSheet characterSheet) {
        HelperMethods.editAtributeByName(atributeName, characterSheet, value);
        return characterSheet;
    }

    @Override
    public CharacterSheet removeData(CharacterSheet characterSheet) {
        HelperMethods.editAtributeByName(atributeName, characterSheet, -value);
        return characterSheet;
    }

    public String getAtributeName() {
        return atributeName;
    }
}
