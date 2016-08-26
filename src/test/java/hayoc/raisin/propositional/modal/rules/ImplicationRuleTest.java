package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.modal.search.PropositionalModalNode;
import hayoc.raisin.setup.GuiceJUnitRunner;
import hayoc.raisin.setup.TestModule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Hayo on 19/08/2016.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class ImplicationRuleTest {

    @Test
    public void testImplicationRule() {
        Rule rule = new ImplicationRule(new PropositionalModalRuleUtilities());
        assertTrue(rule.applicable(new PropositionalModalNode("(A > B), 1")));
        List<Node> resultNodes = rule.apply();
        assertEquals(resultNodes.get(0).getProposition(), "~A, 1");
        assertEquals(resultNodes.get(1).getProposition(), "B, 1");

        assertFalse(rule.applicable(new PropositionalModalNode("(A | B), 1")));
        assertFalse(rule.applicable(new PropositionalModalNode("~(A > B), 1")));
    }
}
