package characterBuilder;

import model.CharacterSheet;

import java.io.Serializable;

public class ClanBuildStep implements BuildStep, Serializable {
    String clan;


    public ClanBuildStep(String clan) {
        this.clan = clan;
    }

    @Override
    public CharacterSheet addData(CharacterSheet characterSheet) {
        characterSheet.getCharacterInfo().setClan(clan);
        return characterSheet;
    }

    @Override
    public CharacterSheet removeData(CharacterSheet characterSheet) {
        characterSheet.getCharacterInfo().setClan("");
        return characterSheet;
    }
}
