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
 * Created by Hayo on 21/08/2016.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class NegatedBiconditionalRuleTest {

    @Test
    public void testNegatedBiconditionalRule() {
        Rule rule = new NegatedBiconditionalRule(new PropositionalClassicalRuleUtilities());
        assertTrue(rule.applicable(new PropositionalClassicalNode("~((A & B) = (B & A))")));

        List<Node> resultNodes = rule.apply();
        assertEquals(resultNodes.get(0).getProposition(), "(A & B)");
        assertEquals(resultNodes.get(0).getChildren().get(0).getProposition(), "~(B & A)");
        assertEquals(resultNodes.get(1).getProposition(), "~(A & B)");
        assertEquals(resultNodes.get(1).getChildren().get(0).getProposition(), "(B & A)");

        assertFalse(rule.applicable(new PropositionalClassicalNode("((A & B) = (B & A))")));
        assertFalse(rule.applicable(new PropositionalClassicalNode("~((A & B) > (B & A))")));
    }
}
