package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdvantageTest {

    private Advantage advantage;

    @Before
    public void before(){
        advantage = new Advantage("Czysty umysł",3);
    }

    @Test
    public void whenCreatedAdvantageHaveNameAndValue(){
        assertEquals(advantage.getName(),"Czysty umysł");
        assertEquals(advantage.getValue(),3);
    }
}