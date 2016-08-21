package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.search.Node;
import hayoc.raisin.search.TableauxSearch;
import hayoc.raisin.setup.GuiceJUnitRunner;
import hayoc.raisin.setup.TestModule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by hayoc on 8/21/2016.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class PropositionalClassicalRuleUtilitiesTest {

    private PropositionalClassicalRuleUtilities ruleUtilities = new PropositionalClassicalRuleUtilities();

    @Test
    public void testIsNegation() {
        Node childNode = new Node("~(A > C)");
        Node parentNode = new Node("(A > C)");
        assertTrue(ruleUtilities.isNegation(childNode, parentNode));

        parentNode.setProposition("~(A > C)");
        assertFalse(ruleUtilities.isNegation(childNode, parentNode));

        childNode.setProposition("(A > C)");
        assertTrue(ruleUtilities.isNegation(childNode, parentNode));
    }

    @Test
    public void testBranchClosed() {
        Node leaf = new Node("A");
        Node subNode = new Node("~(A & C)");
        Node root = new Node("~A");

        leaf.setParent(subNode);
        subNode.setParent(root);
        assertTrue(ruleUtilities.branchClosed(leaf));

        leaf = new Node("~A");
        assertFalse(ruleUtilities.branchClosed(leaf));

        Node leaf2 = new Node("A");
        leaf2.setParent(subNode);
        assertTrue(ruleUtilities.branchClosed(leaf2));
    }
}
