package hayoc.raisin.propositional.classical;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hayo on 16/08/2016.
 */
public class PropositionalClassicalLogicProofTest {

    private final static String GOAL = "(((A > B) & (A > C)) > (A > (B & C)))";

    @Test
    public void testPropositionalClassicalLogicProof() {
        assertTrue(new PropositionalClassicalLogic().prove(GOAL));
    }


}
