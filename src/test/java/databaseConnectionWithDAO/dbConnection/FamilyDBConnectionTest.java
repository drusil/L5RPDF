package databaseConnectionWithDAO.dbConnection;

import databaseConnectionWithDAO.modelDAO.FamilyData;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

import static org.junit.Assert.*;

@Category(IntegrationTests.class)
public class FamilyDBConnectionTest {

  @Test
  public void whenGetFamiliesThenReturnNotNull(){
    List<FamilyData> families = FamilyDBConnection.getAllFamilies();
    assertTrue(!families.isEmpty());
  }

  @Test
  public void whenGetFamiliesByClanNameThenReturnNotNull(){
    List<FamilyData> families = FamilyDBConnection.getAllFamiliesFromClan("feniks");
    assertEquals("Isawa",families.get(0).getName());
  }

}