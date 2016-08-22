package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import hayoc.raisin.setup.GuiceJUnitRunner;
import hayoc.raisin.setup.TestModule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
        PropositionalClassicalNode childNode = new PropositionalClassicalNode("~(A > C)");
        PropositionalClassicalNode parentNode = new PropositionalClassicalNode("(A > C)");
        assertTrue(ruleUtilities.isNegation(childNode, parentNode));

        parentNode.setProposition("~(A > C)");
        assertFalse(ruleUtilities.isNegation(childNode, parentNode));

        childNode.setProposition("(A > C)");
        assertTrue(ruleUtilities.isNegation(childNode, parentNode));
    }

    @Test
    public void testBranchClosed() {
        PropositionalClassicalNode leaf = new PropositionalClassicalNode("A");
        PropositionalClassicalNode subNode = new PropositionalClassicalNode("~(A & C)");
        PropositionalClassicalNode root = new PropositionalClassicalNode("~A");

        leaf.setParent(subNode);
        subNode.setParent(root);
        assertTrue(ruleUtilities.branchClosed(leaf));

        leaf = new PropositionalClassicalNode("~A");
        assertFalse(ruleUtilities.branchClosed(leaf));

        PropositionalClassicalNode leaf2 = new PropositionalClassicalNode("A");
        leaf2.setParent(subNode);
        assertTrue(ruleUtilities.branchClosed(leaf2));
    }
}
