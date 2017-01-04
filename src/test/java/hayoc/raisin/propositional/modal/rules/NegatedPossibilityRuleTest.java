package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.common.Node;
import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.propositional.modal.ModalUtilities;
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
 * Created by Hayo on 28/08/2016.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class NegatedPossibilityRuleTest {

    private ModalUtilities modalUtilities = new ModalUtilities();

    @Test
    public void testNegatedPossibiliyRule() {
        Rule rule = new NegatedPossibilityRule(new PropositionalModalRuleUtilities(modalUtilities), modalUtilities);
        assertTrue(rule.applicable(new PropositionalModalNode("~◊(A = B), 1")));

        List<Node> resultNodes = rule.apply();
        assertEquals(resultNodes.get(0).getProposition(), "□~(A = B), 1");

        assertFalse(rule.applicable(new PropositionalModalNode("◊(A = B), 1")));
        assertFalse(rule.applicable(new PropositionalModalNode("~□(A > B), 1")));

        modalUtilities.clearRelativeWorlds();
    }
}
