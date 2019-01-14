package model;

import model.enums.RingsEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class RingsTest {

    Rings rings;

    @Before
    public void before(){
        rings = new Rings();
    }




    @Test
    public void whenCreatedSetUpProperly(){
        List<String>  elementRings = Arrays.asList(
                new ElementRing(RingsEnum.AIR),
                new ElementRing(RingsEnum.EARTH),
                new ElementRing(RingsEnum.FIRE),
                new ElementRing(RingsEnum.WATER)
        ).stream().map(ElementRing::getName)
                .collect(Collectors.toList());

        assertTrue(elementRings.stream()
        .allMatch((ring) -> rings.getElementRings().stream()
                .map(ElementRing::getName)
                .collect(Collectors.toList())
                .contains(ring)));

        assertEquals("Void", rings.getRingOfVoid().getName());

    }

    @Test
    public void youCanGetRingByName(){
        ElementRing ring = rings.getRing("Air");
        assertEquals("Air", ring.getName());
    }

   @Test
   public void youCanGetRingWithCartaintAtributeByAtributeName(){
      ElementRing ring = rings.getRingWithAtribute("Awareness");
        assertEquals("Air", ring.getName());
   }





}