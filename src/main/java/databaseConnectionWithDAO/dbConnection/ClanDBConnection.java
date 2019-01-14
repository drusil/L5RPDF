package databaseConnectionWithDAO.dbConnection;

import databaseConnectionWithDAO.modelDAO.ClanData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClanDBConnection {
    private static final String DRIVER = "jdbc:derby:L5R;create=true";
    private static final String GET_ALL_CLANS_STATMENT = "SELECT * FROM CLANS";

    private ClanDBConnection() {
    }

    public static List<ClanData> getClans() {
        List<ClanData> clanDataList = new ArrayList<>();
        try (
                Connection myCon = DriverManager.getConnection(DRIVER);
                PreparedStatement stmt = myCon.prepareStatement(GET_ALL_CLANS_STATMENT);
                ResultSet myRS = stmt.executeQuery()) {
            ClanData clanData;
            while (myRS.next()) {
                clanData = new ClanData(myRS.getString("CLAN"), myRS.getString("CLAN_TYPE"));
                clanDataList.add(clanData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clanDataList;
    }

}