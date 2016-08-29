package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.modal.search.PropositionalModalNode;
import hayoc.raisin.setup.GuiceJUnitRunner;
import hayoc.raisin.setup.TestModule;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by hayoc on 8/21/2016.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class PropositionalModalRuleUtilitiesTest {

    private PropositionalModalRuleUtilities ruleUtilities = new PropositionalModalRuleUtilities();

    @Test
    public void testIsNegation() {
        PropositionalModalNode childNode = new PropositionalModalNode("~(A > C), 0");
        PropositionalModalNode parentNode = new PropositionalModalNode("(A > C), 0");
        assertTrue(ruleUtilities.isNegation(childNode, parentNode));

        parentNode.setProposition("~(A > C), 0");
        assertFalse(ruleUtilities.isNegation(childNode, parentNode));

        childNode.setProposition("(A > C), 0");
        assertTrue(ruleUtilities.isNegation(childNode, parentNode));

        parentNode.setProposition("~(A > C), 1");
        assertFalse(ruleUtilities.isNegation(childNode, parentNode));
    }
}
