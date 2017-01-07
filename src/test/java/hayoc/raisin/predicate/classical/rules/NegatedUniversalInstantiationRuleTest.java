package hayoc.raisin.predicate.classical.rules;

import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.predicate.classical.common.VariableConstantMap;
import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Hayo on 05/01/2017.
 */
public class NegatedUniversalInstantiationRuleTest {

    private PredicateClassicalRuleUtilities ruleUtilities = new PredicateClassicalRuleUtilities();
    private VariableConstantMap variableConstantMap = new VariableConstantMap();

    @Test
    public void testNegatedExistentialInstantiationRule() {
        Rule rule = new NegatedUniversalInstantiationRule(ruleUtilities, variableConstantMap);
        assertTrue(rule.applicable(new PropositionalClassicalNode("~∀x((Ax & Bx) = (Bx & Ax))")));

        List<Node> results = rule.apply();
        assertEquals("∃x~((Ax & Bx) = (Bx & Ax))", results.get(0).getProposition());

        testNotApplicable("~∃x(Ax ^ (Bx & Ax))");
        testNotApplicable("∀x((Ax & Bx) = (Bx & Ax))");
    }

    private void testNotApplicable(String proposition) {
        Rule rule = new NegatedUniversalInstantiationRule(ruleUtilities, variableConstantMap);
        assertFalse(rule.applicable(new PropositionalClassicalNode(proposition)));
    }
}
