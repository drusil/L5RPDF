package model.enums;

import java.util.Arrays;
import java.util.List;

public enum Clan {

    PHOENIX("Phoenix", Arrays.asList("Shiba", "Isawa", "Asako")),
    UNICORN("Unicorn", Arrays.asList("Shinjo", "Ide", "Iuchi", "Otaku", "Moto")),
    CRAB("Crab", Arrays.asList("Hida", "Hiruma", "Kaiu", "Kuni", "Yasuki")),
    LION("Lion", Arrays.asList("Akodo", "Matus", "Ikoma", "Kitsu")),
    SCORPION("Scorpion", Arrays.asList("Bayushi", "Shosuro", "Soshi", "Yogo")),
    DRAGON("Dragon", Arrays.asList("Togashi", "Agasha", "Mirumoto", "Kitsuki")),
    CRANE("Crane", Arrays.asList("Doji", "Kakita", "Daidoji", "Asahina"));


    Clan(String name, List<String> families) {
        this.name = name;
        this.families = families;
    }

    private String name;
    private List<String> families;

    public String getName() {
        return name;
    }

    public List<String> getFamilies() {
        return families;
    }
}
