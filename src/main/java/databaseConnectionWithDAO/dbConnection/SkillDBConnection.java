package databaseConnectionWithDAO.dbConnection;

import databaseConnectionWithDAO.modelDAO.SkillData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDBConnection {

    private static final String DRIVER = "jdbc:derby:L5R;create=true";
    private static final String GET_ALL_SKILLS_STATMENT = "SELECT ABILITIES.NAME, ABILITIES_TYPE.TYPE FROM ABILITIES \n" +
            "JOIN ABILITIES_TYPE ON ABILITIES.TYPE_ID = ABILITIES_TYPE.ID ";
    private static final String GET_SKILL_BY_TYPE_STATMENT = "SELECT ABILITIES.NAME, ABILITIES_TYPE.TYPE FROM ABILITIES \n" +
            "JOIN ABILITIES_TYPE ON ABILITIES.TYPE_ID = ABILITIES_TYPE.ID WHERE TYPE = ? ";

    public static List<SkillData> getAllSkills() {
        List<SkillData> skillDataList = new ArrayList<>();

        try (Connection myCon = DriverManager.getConnection(DRIVER);
             PreparedStatement stmt = myCon.prepareStatement(GET_ALL_SKILLS_STATMENT);
             ResultSet myRS = stmt.executeQuery()) {
            SkillData skillData;
            while (myRS.next()) {
                skillData = new SkillData(myRS.getString("NAME"), myRS.getString("TYPE"));
                skillDataList.add(skillData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillDataList;
    }

    public static List<SkillData> getSkillsByType(String type) {
        List<SkillData> skillDataList = new ArrayList<>();

        try {
            Connection myCon = DriverManager.getConnection(DRIVER);
            PreparedStatement stmt = myCon.prepareStatement(GET_SKILL_BY_TYPE_STATMENT);
            myCon.setAutoCommit(false);
            stmt.setString(1, type);
            stmt.execute();
            myCon.commit();
            ResultSet myRS = stmt.getResultSet();
            SkillData skillData;
            while (myRS.next()) {
                skillData = new SkillData(myRS.getString("NAME"), myRS.getString("TYPE"));
                skillDataList.add(skillData);
            }

            myRS.close();
            stmt.close();
            myCon.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillDataList;
    }

    public static List<SkillData> getGodneSkills() {
        return getSkillsByType("Godne");
    }

    public static List<SkillData> getBugeiSkills() {
        return getSkillsByType("Bugei");

    }

    public static List<SkillData> getKupieckieSkills() {
        return getSkillsByType("Kupieckie");

    }

    public static List<SkillData> getNiegodneSkills() {
        return getSkillsByType("Niegodne");

    }
}
