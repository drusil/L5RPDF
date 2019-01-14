package consoleCMDInterface.create;

import characterBuilder.AtributeBuildStep;
import characterBuilder.CharacterBuilder;
import consoleCMDInterface.HelperMetods;
import model.ElementRing;
import model.Rings;

import java.util.List;
import java.util.Scanner;

public class EditAtributes extends CreationStage {
    Rings rings;
    public EditAtributes(CharacterBuilder characterBuilder, Scanner scanner, String name) {
        super(characterBuilder, scanner, name);
    }



    @Override
    public void run() {
        rings = characterBuilder.getCharacterSheet().getRings();
        int choice =0;
        int exitChoiceValue =6;
        do {
            printMenu();
            choice = HelperMetods.scanIntValue(scanner,1,6);

            if(choice <=4){
                editRingValue(choice);
            }
            else if(choice ==5){
                editVoidValue();
            }
        } while (choice != exitChoiceValue);
    }

    private void editVoidValue() {
        System.out.println("Edit Void");
        int value = HelperMetods.scanIntValue(scanner);
        characterBuilder.addAtributeStep(new AtributeBuildStep("Void",value));
    }

    private void editRingValue(int ringIndex) {
        ElementRing elementRing = rings.getElementRings().get(ringIndex-1);

        int choice =0;
        int exitValue =3;
        do {
            System.out.println(elementRing.getName() +" " + elementRing.getValue());
            System.out.println("1) " + elementRing.getPhysicalAttributeName() + " " + elementRing.getPhysicalAttributeValue());
            System.out.println("2) " + elementRing.getMentalAttributeName() + " " + elementRing.getMentalAttributeValue());
            System.out.println("3) Exit" );

            choice = HelperMetods.scanIntValue(scanner,1,3);
            if(choice == 1) editAtribute(elementRing.getPhysicalAttributeName());
            if(choice == 2) editAtribute(elementRing.getMentalAttributeName());

        } while (choice != exitValue);
    }

    private void editAtribute(String atributeName){
        int value = HelperMetods.scanIntValue(scanner,1,10);
        characterBuilder.addAtributeStep(new AtributeBuildStep(atributeName,value));

    }

    private void printMenu() {
        List<ElementRing> elementRings = rings.getElementRings();
        rings.getElementRings().stream()
                .forEachOrdered(r -> System.out.println(elementRings.indexOf(r) +1 +") " + r.toString()));
        System.out.println("5) "+ rings.getRingOfVoid().toString());
        System.out.println("6) Exit");


    }
}
