package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.setup.GuiceJUnitRunner;
import hayoc.raisin.setup.TestModule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by hayoc on 8/20/2016.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class NegatedConjunctionRuleTest {

    @Test
    public void testNegatedConjunctionRule() {
        Rule rule = new NegatedConjunctionRule(new PropositionalClassicalRuleUtilities());
        assertTrue(rule.applicable(new PropositionalClassicalNode("~(((A > B) & (A > C)) & (A > (B & C)))")));
        List<Node> resultNodes = rule.apply();
        assertEquals(resultNodes.get(0).getProposition(), "~((A > B) & (A > C))");
        assertEquals(resultNodes.get(1).getProposition(), "~(A > (B & C))");

        assertTrue(rule.applicable(new PropositionalClassicalNode("~(A & B)")));
        resultNodes = rule.apply();
        assertEquals(resultNodes.get(0).getProposition(), "~A");
        assertEquals(resultNodes.get(1).getProposition(), "~B");

        assertFalse(rule.applicable(new PropositionalClassicalNode("~(((A > B) & (A > C)) > (A > (B & C)))")));
        assertFalse(rule.applicable(new PropositionalClassicalNode("(((A > B) & (A > C)) & (A > (B & C)))")));
    }
}
