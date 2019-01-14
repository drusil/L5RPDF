package characterBuilder;

import model.CharacterSheet;

public interface BuildStep {
    public CharacterSheet addData(CharacterSheet characterSheet);

    public CharacterSheet removeData(CharacterSheet characterSheet);
}
