package model;

import model.enums.RingsEnum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static model.enums.MinMaxValues.*;

public class ElementRingTest {

    ElementRing elementRing;

    @Before
    public void before(){
        elementRing = new ElementRing(RingsEnum.AIR);
    }

    @Test
    public void youCreateRingFromEnum(){


        assertEquals("Air", elementRing.getName());
        assertEquals("Awerness", elementRing.getMentalAttributeName());
        assertEquals("Reflexes", elementRing.getPhysicalAttributeName());
    }

    @Test
    public void whenCreatedValuesSetOn0(){

        assertEquals(0, elementRing.getValue());
        assertEquals(0, elementRing.getMentalAttributeValue());
        assertEquals(0, elementRing.getPhysicalAttributeValue());
    }

    @Test
    public void whenAttributeValueSetUpOutsideBoundariesChangetToMinOrMax(){
        elementRing.setMentalAttributeValue(12);
        assertEquals(MAX_VALUE, elementRing.getMentalAttributeValue());

        elementRing.setMentalAttributeValue(-4);
        assertEquals(MIN_VALUE, elementRing.getMentalAttributeValue());

        elementRing.setPhysicalAttributeValue(14);
        assertEquals(MAX_VALUE, elementRing.getPhysicalAttributeValue());

        elementRing.setPhysicalAttributeValue(-3);
        assertEquals(MIN_VALUE, elementRing.getPhysicalAttributeValue());
    }

    @Test
    public void whenAttributeValuesIsChangedRingValueIsCalculated(){
        elementRing.setPhysicalAttributeValue(3);
        elementRing.setMentalAttributeValue(5);
        assertEquals(3, elementRing.getValue());
    }

}