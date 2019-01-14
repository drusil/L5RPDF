package consoleCMDInterface.create;

import characterBuilder.CharacterBuilder;
import characterBuilder.FamilyBuidStep;
import consoleCMDInterface.HelperMetods;
import databaseConnectionWithDAO.dbConnection.FamilyDBConnection;
import databaseConnectionWithDAO.modelDAO.FamilyData;

import java.util.List;
import java.util.Scanner;

public class ChoseFamily extends CreationStage {
    List<FamilyData> families;

    public ChoseFamily(CharacterBuilder characterBuilder, Scanner scanner, String name) {
        super(characterBuilder, scanner, name);
    }

    @Override
    public void run() {
        String clanChosen = characterBuilder.getCharacterSheet().getCharacterInfo().getClan();
        if (clanChosen.equalsIgnoreCase("")) {
            System.out.println("First chose clan");
            return;
        }
        families = FamilyDBConnection.getAllFamiliesFromClan(clanChosen);
        families.stream()
                .forEach(fd -> System.out.println(families.indexOf(fd) + 1 + ") " + fd.toString()));
        int choice = HelperMetods.scanIntValue(scanner, 1, families.size());
        choseFamily(choice - 1);

    }

    private void choseFamily(int choice) {
        FamilyData chosenFamily = families.get(choice);
        characterBuilder.setFamilyBuidStep(new FamilyBuidStep(chosenFamily));
    }
}
