package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.Rule;
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
public class NecessityRuleTest {

    @Test
    public void testNecessityRule() {
        Rule rule = new NecessityRule(new PropositionalModalRuleUtilities());
        assertFalse(rule.applicable(new PropositionalModalNode("□(A = B), 1")));

        ModalUtilities.addRelativeWorld(1, 2);
        assertTrue(rule.applicable(new PropositionalModalNode("□(A = B), 1")));

        List<Node> resultNodes = rule.apply();
        assertEquals(resultNodes.get(0).getProposition(), "(A = B), 2");

        assertFalse(rule.applicable(new PropositionalModalNode("~□(A = B), 1")));
        assertFalse(rule.applicable(new PropositionalModalNode("◊(A > B), 1")));

        ModalUtilities.clearRelativeWorlds();
    }
}
