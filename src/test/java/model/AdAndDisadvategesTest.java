package model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class AdAndDisadvategesTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    AdAndDisadvateges adAndDis;
    Advantage advantage;
    Advantage disadvantage;

     @Before
     public void before(){
         adAndDis = new AdAndDisadvateges();
         advantage = new Advantage("Czysty Umysł", 3);
         disadvantage = new Advantage("Kiepski Kłamca", -2);
     }


    @Test
    public void youCanAddAdvantageAndDisadvantage(){

        adAndDis.addAdvantage(advantage);
        assertTrue(adAndDis.getAdvantages().contains(advantage));


        adAndDis.addDisadvantage(disadvantage);
        assertTrue(adAndDis.getDisadvantages().contains(disadvantage));

    }

    @Test
    public void whenYouAddAdvantageWithTheSameNameMultipleTimesThenRuntimeException(){
         adAndDis.addAdvantage(advantage);
         exception.expect(RuntimeException.class);
         adAndDis.addAdvantage(new Advantage("Czysty Umysł", 3));

    }

    @Test
    public void whenYouAddDisadvantageWithTheSameNameMultipleTimesThenRuntimeException(){

        adAndDis.addDisadvantage(disadvantage);
        exception.expect(RuntimeException.class);
        adAndDis.addDisadvantage(new Advantage("Kiepski Kłamca", -2));
    }


}