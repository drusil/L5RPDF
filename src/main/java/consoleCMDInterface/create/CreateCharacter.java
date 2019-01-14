package consoleCMDInterface.create;

import characterBuilder.CharacterBuilder;
import consoleCMDInterface.HelperMetods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CreateCharacter extends CreationStage {
    List<CreationStage> creationStages;


    public CreateCharacter(CharacterBuilder characterBuilder, Scanner scanner, String name) {
        super(characterBuilder, scanner, name);
    }

    private void setUpCreeationStages() {
        creationStages = new ArrayList<>();
        creationStages.add(new ChoseClan(characterBuilder, scanner, "Clan"));
        creationStages.add(new ChoseFamily(characterBuilder, scanner, "Family"));
        creationStages.add(new EditAtributes(characterBuilder, scanner, "Atributes"));
        creationStages.add(new ChoseSkills(characterBuilder, scanner, "Skills"));

    }


    @Override
    public void run() {

        setUpCreeationStages();
        int choice = 0;
        int exitChoice = creationStages.size() + 2;
        do {
            printMenu();
            choice = HelperMetods.scanIntValue(scanner, 1, exitChoice);
            if (choice <= creationStages.size()) {
                creationStages.get(choice - 1).run();
            } else if (choice == creationStages.size() + 1) {
                System.out.println(characterBuilder.getCharacterSheet().toString());
            }
        } while (choice != exitChoice);
        super.setName(characterBuilder.getName());
    }

    private void printMenu() {
        creationStages.stream()
                .forEach(cs -> System.out.printf("%d) %s %n", creationStages.indexOf(cs) + 1, cs.getName()));

        System.out.printf("%d) Show Character %n", creationStages.size() + 1);
        System.out.printf("%d) Exit %n", creationStages.size() + 2);
    }

}
