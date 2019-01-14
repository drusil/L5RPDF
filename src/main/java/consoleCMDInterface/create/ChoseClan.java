package consoleCMDInterface.create;

import characterBuilder.CharacterBuilder;
import characterBuilder.ClanBuildStep;
import consoleCMDInterface.HelperMetods;
import databaseConnectionWithDAO.dbConnection.ClanDBConnection;
import databaseConnectionWithDAO.modelDAO.ClanData;

import java.util.List;
import java.util.Scanner;

public class ChoseClan extends CreationStage {
    List<ClanData> clanData;

    public ChoseClan(CharacterBuilder characterBuilder, Scanner scanner, String name) {
        super(characterBuilder, scanner, name);
    }

    @Override
    public void run() {
        getClanData();
        System.out.println("Chose your clan:");
        clanData.stream()
                .forEach(cd -> System.out.println(clanData.indexOf(cd) + 1 + ") " + cd.toString()));

        int choice = HelperMetods.scanIntValue(scanner, 1, clanData.size());
        updateClanChoice(choice - 1);
        System.out.println(characterBuilder.getCharacterSheet());


    }

    private void getClanData() {
        clanData = ClanDBConnection.getClans();
    }

    private void updateClanChoice(int choice) {
        String clanName = clanData.get(choice).getClanName();
        characterBuilder.setClanBuildStep(new ClanBuildStep(clanName));
    }
}
