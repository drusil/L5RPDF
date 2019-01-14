package databaseConnectionWithDAO.dbConnection;

import databaseConnectionWithDAO.modelDAO.SkillData;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

import static org.junit.Assert.*;

@Category(IntegrationTests.class)
public class SkillDBConnectionTest {

  @Test
  public void whenGetGodneSkillsThenReturnIsNotEmpty(){
    List<SkillData> data = SkillDBConnection.getGodneSkills();
    assertFalse(data.isEmpty());
  }

  @Test
  public void whenGetBugeiSkillsThenReturnIsNotEmpty(){
    List<SkillData> data = SkillDBConnection.getBugeiSkills();
    assertFalse(data.isEmpty());
  }

  @Test
  public void whenGetKupieckieSkillsThenReturnIsNotEmpty(){
    List<SkillData> data = SkillDBConnection.getKupieckieSkills();
    assertFalse(data.isEmpty());
  }

  @Test
  public void whenGetNiegodneSkillsThenReturnIsNotEmpty(){
    List<SkillData> data = SkillDBConnection.getNiegodneSkills();
    assertFalse(data.isEmpty());
  }

}