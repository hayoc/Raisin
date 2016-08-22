package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.search.Node;
import hayoc.raisin.setup.GuiceJUnitRunner;
import hayoc.raisin.setup.TestModule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Hayo on 21/08/2016.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class DoubleNegationRuleTest {

    @Test
    public void testDisjunctionRule() {
        PropositionalClassicalRule rule = new DoubleNegationRule(new PropositionalClassicalRuleUtilities());
        assertTrue(rule.applicable(new Node("~~(A & B)")));
        List<Node> resultNodes = rule.apply();
        assertEquals(resultNodes.get(0).getProposition(), "(A & B)");

        assertTrue(rule.applicable(new Node("~~((A & B) & (B & A))")));
        assertFalse(rule.applicable(new Node("~((A & B) | (B & A))")));
        assertFalse(rule.applicable(new Node("((A & B) | (B & A))")));
    }
}