package model;

import org.junit.Test;

import static org.junit.Assert.*;
import static model.enums.MinMaxValues.*;

public class VoidElementRingTest {

    private RingOfVoid ringOfVoid;

    @Test
    public void  whenValueSetUpOutsideBoundariesChangetToMinOrMax() {
        ringOfVoid = new RingOfVoid();

        ringOfVoid.setValue(12);
        assertEquals(MAX_VALUE, ringOfVoid.getValue());

        ringOfVoid.setValue(-12);
        assertEquals(MIN_VALUE, ringOfVoid.getValue());

    }

}