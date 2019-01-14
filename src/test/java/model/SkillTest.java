package model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SkillTest {

    private Skill skill;

    @Before
    public void before(){
        skill = new Skill("Kenjutsu");
    }

    @Test
    public void youCanAddSpecializtion(){
        skill.addSpec("katana");
        skill.addSpec("nodachi");

        String spec = Arrays.asList("katana","nodachi").toString();
        assertEquals(spec,skill.getSpecializations().toString());

    }

    @Test
    public void youCantAddSpecializationDuplicate() {
       assertTrue(skill.addSpec("katana"));
       assertFalse(skill.addSpec("katana"));
    }


}