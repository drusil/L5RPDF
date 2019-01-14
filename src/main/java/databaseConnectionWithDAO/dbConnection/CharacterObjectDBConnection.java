package databaseConnectionWithDAO.dbConnection;

import characterBuilder.CharacterBuilder;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterObjectDBConnection {
    private static final String DRIVER = "jdbc:derby:L5R;create=true";
    private static final String UPDATE_CHARACTER_STATMENT = "UPDATE CHARACTERS SET NAME = ?, DATA = ? WHERE ID = ?";
    private static final String SAVE_NEW_CHARACTER_STATMENT = "INSERT INTO CHARACTERS (NAME, DATA) VALUES (?,?)";
    private static final String LOAD_ALL_CHARACTERS_STATMENT = "SELECT * FROM CHARACTERS";
    private static final String DELETE_CHARACTER_STATMENT = "DELETE FROM CHARACTERS WHERE ID = ? ";

    private CharacterObjectDBConnection() {
    }


    public static List<CharacterBuilder> getCharactersFromDB() {

        List<CharacterBuilder> characterBuilders = new ArrayList<>();

        try
                (Connection myConn = DriverManager.getConnection(DRIVER);

                 PreparedStatement myStmt = myConn.prepareStatement(LOAD_ALL_CHARACTERS_STATMENT);

                 ResultSet myRs = myStmt.executeQuery()) {
            while (myRs.next()) {
                CharacterBuilder characterBuilder = loadCharacterFromByteArray(myRs.getBytes("DATA"));
                if (characterBuilder != null) {
                    characterBuilder.getName();
                    characterBuilder.setId(myRs.getInt("ID"));
                    characterBuilders.add(characterBuilder);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characterBuilders;
    }


    public static void saveCharactersToDB(List<CharacterBuilder> characters) {
        for (CharacterBuilder character : characters
                ) {
            if (character.getId() == -1) {
                saveNewCharacterInDatabase(character);
            } else {
                updateCharacterInDatabase(character);
            }
        }
    }

    public static void deleteCharacterFromDB(CharacterBuilder characterBuilder) {
        int id = characterBuilder.getId();
        try (Connection myConn = DriverManager.getConnection(DRIVER);
        ) {
            myConn.setAutoCommit(false);
            PreparedStatement stmt = myConn.prepareStatement(DELETE_CHARACTER_STATMENT);
            stmt.setInt(1, id);
            stmt.execute();
            myConn.commit();


        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    private static void updateCharacterInDatabase(CharacterBuilder characterBuilder) {

        byte[] byteCharacter = saveCharacterToByteArray(characterBuilder);
        if (byteCharacter != null) {

            try
                    (Connection myConn = DriverManager.getConnection(DRIVER)) {
                myConn.setAutoCommit(false);
                PreparedStatement stmt = myConn.prepareStatement(UPDATE_CHARACTER_STATMENT);
                stmt.setString(1, characterBuilder.getName());
                stmt.setBinaryStream(2, new ByteArrayInputStream(byteCharacter), byteCharacter.length);
                stmt.setInt(3, characterBuilder.getId());

                stmt.execute();
                myConn.commit();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static CharacterBuilder loadCharacterFromByteArray(byte[] data) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInput in = new ObjectInputStream(bis)) {
            return (CharacterBuilder) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void saveNewCharacterInDatabase(CharacterBuilder characterBuilder) {

        byte[] byteCharacter = saveCharacterToByteArray(characterBuilder);
        if (byteCharacter != null) {

            try
                    (Connection myConn = DriverManager.getConnection(DRIVER);
                     PreparedStatement stmt = myConn.prepareStatement(SAVE_NEW_CHARACTER_STATMENT)
                    ) {
                myConn.setAutoCommit(false);


                stmt.setString(1, characterBuilder.getName());
                stmt.setBinaryStream(2, new ByteArrayInputStream(byteCharacter), byteCharacter.length);

                stmt.execute();
                myConn.commit();
                //TODO Update ID after inserting into DB

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static byte[] saveCharacterToByteArray(CharacterBuilder characterBuilder) {
        byte[] byteChar = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(characterBuilder);
            out.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteChar;
    }
}
