package databaseConnectionWithDAO.dbConnection;

import databaseConnectionWithDAO.modelDAO.FamilyData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FamilyDBConnection {

    private static final String DRIVER = "jdbc:derby:L5R;create=true";


    private FamilyDBConnection() {
    }

    public static List<FamilyData> getAllFamilies() {
        List<FamilyData> families = new ArrayList<>();


        try (Connection myCon = DriverManager.getConnection(DRIVER);
             Statement myStat = myCon.createStatement();
             ResultSet myRs = myStat.executeQuery("SELECT FAMILIES.NAME AS NAME,"
                     + "ATRIBUTES.NAME AS ATRIBUTE,"
                     + "CLANS.CLAN AS CLAN "
                     + "FROM FAMILIES "
                     + "JOIN CLANS ON FAMILIES.CLAN_ID = CLANS.ID "
                     + "JOIN ATRIBUTES ON FAMILIES.BONUS_ATRIBUTE = ATRIBUTES.ID")) {
            while (myRs.next()) {
                families.add(new FamilyData(myRs.getString("NAME"), myRs.getString("ATRIBUTE"), myRs.getString("CLAN")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return families;
    }

    public static List<FamilyData> getAllFamiliesFromClan(String clanName) {
        List<FamilyData> families = new ArrayList<>();

        try (Connection myCon = DriverManager.getConnection(DRIVER);
             Statement myStat = myCon.createStatement();
             ResultSet myRs = myStat.executeQuery("SELECT FAMILIES.NAME AS NAME,\n" +
                     "            ATRIBUTES.NAME AS ATRIBUTE,\n" +
                     "            CLANS.CLAN AS CLAN \n" +
                     "            FROM FAMILIES \n" +
                     "            JOIN CLANS ON FAMILIES.CLAN_ID = CLANS.ID \n" +
                     "            JOIN ATRIBUTES ON FAMILIES.BONUS_ATRIBUTE = ATRIBUTES.ID\n" +
                     "            WHERE CLAN = \'" + clanName + "\'")) {
            while (myRs.next()) {
                families.add(new FamilyData(myRs.getString("NAME"), myRs.getString("ATRIBUTE"), myRs.getString("CLAN")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return families;
    }

}
