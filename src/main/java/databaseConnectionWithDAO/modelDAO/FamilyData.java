package databaseConnectionWithDAO.modelDAO;

import java.io.Serializable;

public class FamilyData implements Serializable {
    private String name;
    private String atributeBonus;
    private String clanOfOrigin;

    public FamilyData(String name, String atributeBonus, String clanOfOrigin) {
        this.name = name;
        this.atributeBonus = atributeBonus;
        this.clanOfOrigin = clanOfOrigin;
    }

    public String getName() {
        return name;
    }

    public String getAtributeBonus() {
        return atributeBonus;
    }

    public String getClanOfOrigin() {
        return clanOfOrigin;
    }

    @Override
    public String toString() {
        return name + "  +1" + atributeBonus;
    }
}

