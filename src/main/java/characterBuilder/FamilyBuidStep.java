package characterBuilder;

import databaseConnectionWithDAO.modelDAO.FamilyData;
import model.CharacterSheet;

import java.io.Serializable;

public class FamilyBuidStep implements BuildStep, Serializable {
    FamilyData familyData;

    public FamilyBuidStep(FamilyData familyData) {
        this.familyData = familyData;
    }

    @Override
    public CharacterSheet addData(CharacterSheet characterSheet) {
        HelperMethods.editAtributeByName(familyData.getAtributeBonus(), characterSheet, 1);
        characterSheet.getCharacterInfo().setFamily(familyData.getName());
        return characterSheet;
    }

    @Override
    public CharacterSheet removeData(CharacterSheet characterSheet) {
        HelperMethods.editAtributeByName(familyData.getAtributeBonus(), characterSheet, -1);
        characterSheet.getCharacterInfo().setFamily("");
        return characterSheet;
    }


}
