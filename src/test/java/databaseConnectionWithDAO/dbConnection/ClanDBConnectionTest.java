package databaseConnectionWithDAO.dbConnection;

import databaseConnectionWithDAO.modelDAO.ClanData;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

import static org.junit.Assert.*;

@Category(IntegrationTests.class)
public class ClanDBConnectionTest {

  @Test
  public void  whenGetClansThenReturnIsNoEmpty(){
    List<ClanData > clans = ClanDBConnection.getClans();
    assertTrue(!clans.isEmpty());
  }

}