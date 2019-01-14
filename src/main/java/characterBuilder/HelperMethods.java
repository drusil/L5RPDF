package characterBuilder;

import model.CharacterSheet;
import model.ElementRing;

import java.io.Serializable;

public class HelperMethods implements Serializable {
    public static void editAtributeByName(String name, CharacterSheet characterSheet, int value) {
        if (name.equalsIgnoreCase("Void")) {
            int newValue = characterSheet.getRings().getRingOfVoid().getValue() + value;
            characterSheet.getRings().getRingOfVoid().setValue(newValue);
            return;
        }
        ElementRing elementRing = characterSheet.getRings().getRingWithAtribute(name);
        if (elementRing.getMentalAttributeName().equalsIgnoreCase(name)) {
            elementRing.setMentalAttributeValue(elementRing.getMentalAttributeValue() + value);
        } else {
            elementRing.setPhysicalAttributeValue(elementRing.getPhysicalAttributeValue() + value);
        }
    }
}
