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
 * Created by id094697 on 05/01/2017.
 */
public class ExistentialInstantiationRuleTest {

    private PredicateClassicalRuleUtilities ruleUtilities = new PredicateClassicalRuleUtilities();
    private VariableConstantMap variableConstantMap = new VariableConstantMap();

    @Test
    public void testExistentialInstantiationRule() {
        testApplicableRule("∃x((Ax & Bx) = (Bx & Ax))", "x", "0");
        testApplicableRule("∃y(Ay ^ (By & Ay))", "y", "1");
        testApplicableRule("∃z(Az ^ (Bz & Az))", "z", "2");
        testNotApplicable("∀x(Ax ^ (Bx & Ax))");
        testNotApplicable("∃((Ax & Bx = (Bx & Ax))");
    }

    private void testNotApplicable(String proposition) {
        Rule rule = new ExistentialInstantiationRule(ruleUtilities, variableConstantMap);
        assertFalse(rule.applicable(new PropositionalClassicalNode(proposition)));
    }

    private void testApplicableRule(String proposition, String variable, String constant) {
        Rule rule = new ExistentialInstantiationRule(ruleUtilities, variableConstantMap);
        assertTrue(rule.applicable(new PropositionalClassicalNode(proposition)));

        List<Node> results = rule.apply();
        assertEquals(proposition.substring(2).replace(variable, constant), results.get(0).getProposition());
    }
}
