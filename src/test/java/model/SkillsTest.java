package model;

import org.junit.Before;
import org.junit.Test;

import javax.swing.text.StyledEditorKit;

import static org.junit.Assert.*;

public class SkillsTest {
    private Skills skills;

    @Before
    public void before(){
         skills = new Skills();
    }

    @Test
    public void whenSkillAddedReturnTrue(){
        assertTrue(skills.addSkill(new Skill("Kenjutsu")));
    }

    @Test
    public void cantAddDuplicateSkill(){
        // new skill
        assertTrue(skills.addSkill(new Skill("Kenjutsu")));
        // skill duplicate
        assertFalse(skills.addSkill(new Skill("Kenjutsu")));

    }

    @Test
    public void ifSkillIsOnTheListThenReturnTrue(){
        assertFalse(skills.containsSkill("kenjutsu"));
        skills.addSkill(new Skill("Kenjutsu"));
        assertTrue(skills.containsSkill("kenjutsu"));
    }

    @Test
    public void getSkillUsingSkillName(){
        Skill skill = new Skill("Kenjutsu");
        skill.setValue(3);
        skills.addSkill(skill);
        assertEquals(3,skills.getSkillByName("kenjutsu").getValue());
    }

    @Test
    public void ifSkillDontExistThenReturnDoNotExist(){
        assertEquals("DoNotExist",skills.getSkillByName("kenjutsu").getName());
    }

}