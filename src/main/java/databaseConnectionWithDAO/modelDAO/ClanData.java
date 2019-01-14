package databaseConnectionWithDAO.modelDAO;

import java.io.Serializable;

public class ClanData implements Serializable {
    private String clanName;
    private String clanType;

    public ClanData(String clanName, String clanType) {
        this.clanName = clanName;
        this.clanType = clanType;
    }

    public String getClanName() {
        return clanName;
    }

    public String getClanType() {
        return clanType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(clanName);
        sb.append(" ").append(clanType);
        return sb.toString();
    }
}
