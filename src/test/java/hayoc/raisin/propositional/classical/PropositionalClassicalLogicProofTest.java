package hayoc.raisin.propositional.classical;

import hayoc.raisin.propositional.common.PropositionalUtilities;
import hayoc.raisin.setup.GuiceJUnitRunner;
import hayoc.raisin.setup.TestModule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hayo on 16/08/2016.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class PropositionalClassicalLogicProofTest {

    private final static String GOAL = "(((A > B) & (A > C)) > (A > (B & C)))";

    @Inject
    private PropositionalClassicalLogic propositionalClassicalLogic;

    @Test
    public void testPropositionalClassicalLogicProof() {
        assertTrue(propositionalClassicalLogic.prove(GOAL));
    }


}
