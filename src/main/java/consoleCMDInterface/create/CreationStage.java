package consoleCMDInterface.create;

import characterBuilder.CharacterBuilder;

import java.util.Scanner;

public abstract class CreationStage {

    CharacterBuilder characterBuilder;
    protected Scanner scanner;
    private String name;


    public CreationStage(CharacterBuilder characterBuilder, Scanner scanner, String name) {
        this.characterBuilder = characterBuilder;
        this.scanner = scanner;
        this.name = name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void run();

    public CharacterBuilder getCharacterBuilder() {
        return characterBuilder;
    }
}
