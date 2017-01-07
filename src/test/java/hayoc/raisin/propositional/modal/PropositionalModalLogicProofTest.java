package hayoc.raisin.propositional.modal;

import hayoc.raisin.setup.GuiceJUnitRunner;
import hayoc.raisin.setup.TestModule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hayo on 08/01/2017.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class PropositionalModalLogicProofTest {

    private final static String GOAL = "(◊(A & B) > (◊A & ◊B)), 1";

    @Inject
    private PropositionalModalLogic propositionalModalLogic;

    @Test
    public void testPropositionalClassicalLogicProof() {
        assertTrue(propositionalModalLogic.prove(GOAL));
    }
}
