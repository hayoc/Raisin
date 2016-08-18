package hayoc.raisin.propositional.common;

import hayoc.raisin.propositional.classical.PropositionalClassicalLogic;
import hayoc.raisin.setup.GuiceJUnitRunner;
import hayoc.raisin.setup.TestModule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Hayo on 17/08/2016.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class PropositionalUtilitiesTest {

    private static final String TARGET = "~(((A > B) & (A > C)) > (A > (B & C)))";

    @Inject
    private PropositionalUtilities propositionalUtilities;

    @Test
    public void testSimpleNegation() {
        try {
            assertEquals(propositionalUtilities.negate("(((A > B) & (A > C)) > (A > (B & C)))"), TARGET);
        } catch (PropositionalSyntaxException e) {
            fail();
        }
    }

    @Test
    public void testNegationWithMissingParentheses() {
        try {
            assertEquals(propositionalUtilities.negate("((A > B) & (A > C)) > (A > (B & C))"), TARGET);
        } catch (PropositionalSyntaxException e) {
            fail();
        }
    }

    @Test(expected=PropositionalSyntaxException.class)
    public void testSyntaxError() throws PropositionalSyntaxException {
        propositionalUtilities.negate("((A > B & (A > C)) > (A > (B & C))");
    }
}
