package databaseConnectionWithDAO.dbConnection;

import characterBuilder.CharacterBuilder;
import characterBuilder.FamilyBuidStep;
import characterBuilder.SkillBuildStep;
import databaseConnectionWithDAO.modelDAO.FamilyData;
import model.Skill;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Category(IntegrationTests.class)
public class CharacterObjectDBConnectionTest {
  CharacterBuilder character1;
  List<CharacterBuilder> characters;
  @Before
  public void before(){
    characters = new ArrayList<>();
    character1 = new CharacterBuilder();
    character1.setFamilyBuidStep(new FamilyBuidStep(new FamilyData("Moto Hida Lima","Void","Feniks")));
    character1.setId(1);
    character1.addSkillBuildStep(new SkillBuildStep(new Skill("kenjutsu",3)));
    characters.add(character1);

  }

  @Test
  public void characterIsAddedProperlyToDatabase(){




  }

}