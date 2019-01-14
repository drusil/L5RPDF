package consoleCMDInterface;

import characterBuilder.CharacterBuilder;
import consoleCMDInterface.create.CreateCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static databaseConnectionWithDAO.dbConnection.CharacterObjectDBConnection.*;

public class MainMenu {
    List<CreateCharacter> menuChoice;
    Scanner scanner;

    public void run() {
        scanner = new Scanner(System.in);
        menuChoice = new ArrayList<>();
        loadCharacters();

        int choice = 0;
        int exitChoice;
        do {


            addNewCharacterOptionToListIfNotPresent();

            menuChoice.stream().forEach(ch -> System.out.printf("%d) %s%n", menuChoice.indexOf(ch) + 1, ch.getName()));

            int deleteChoice = menuChoice.size() + 1;
            exitChoice = menuChoice.size() + 2;

            System.out.printf("%d) Delete Character%n", menuChoice.size() + 1);
            System.out.printf("%d) Exit Character%n", menuChoice.size() + 2);

            choice = HelperMetods.scanIntValue(scanner, 1, exitChoice);

            if (choice >= 1 && choice <= menuChoice.size()) {
                menuChoice.get(choice - 1).run();
                System.out.println("Run");
            } else if (choice == deleteChoice) {
                deleteCharacter();
            }

        } while (choice != exitChoice);

        saveCharacters();


    }


    public void loadCharacters() {

        List<CharacterBuilder> characters = getCharactersFromDB();
        characters.stream().forEach(character -> menuChoice.add(new CreateCharacter(character, scanner, character.getName())));

    }

    public void saveCharacters() {
        List<CharacterBuilder> characters = menuChoice.stream()
                .map(CreateCharacter::getCharacterBuilder)
                .collect(Collectors.toList());
        saveCharactersToDB(characters);
    }

    private void addNewCharacterOptionToListIfNotPresent() {
        if ((menuChoice.isEmpty()) || (!menuChoice.isEmpty() && menuChoice.get(menuChoice.size() - 1).getName() != "NEW CHARACTER")) {
            menuChoice.add(new CreateCharacter(new CharacterBuilder(), scanner, "NEW CHARACTER"));
        }

    }


    private void deleteCharacter() {
        if (menuChoice.size() == 1) {
            System.out.println("no character to delete");
            return;
        }
        int exitChoice = menuChoice.size();
        int choice = 0;
        do {
            printCharactersToDelete();
            choice = HelperMetods.scanIntValue(scanner, 1, menuChoice.size() - 1);
            if (choice != exitChoice) {
                System.out.println("If you sure type \" YES \"");
                String areYouSure = HelperMetods.scanStringValue(scanner);
                if (areYouSure.trim().equalsIgnoreCase("yes")) {
                    deleteCharacterFromDB(menuChoice.get(choice - 1).getCharacterBuilder());
                    menuChoice.remove(choice - 1);
                    return;
                }
            }
        } while (choice != exitChoice);

    }

    private void printCharactersToDelete() {
        int menuoptionSize = menuChoice.size();
        int i = 0;
        for (; i < menuoptionSize - 1; i++) {
            System.out.printf("%d) %s%n", i + 1, menuChoice.get(i).getName());
        }
        System.out.printf("%d) Exit", i);
    }
}
