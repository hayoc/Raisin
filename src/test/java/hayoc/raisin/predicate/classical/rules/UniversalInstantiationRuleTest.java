package hayoc.raisin.predicate.classical.rules;

import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.predicate.classical.common.ConstantList;
import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by id094697 on 05/01/2017.
 */
public class UniversalInstantiationRuleTest {

    private PredicateClassicalRuleUtilities ruleUtilities = new PredicateClassicalRuleUtilities();
    private ConstantList constantList = new ConstantList();

    @Test
    public void testUniversalInstantiationRule() {
        testApplicableRule("∀x((Ax & Bx) = (Bx & Ax))", "0");
        testApplicableRule("∀x(Ax ^ (Bx & Ax))", "0");
        testApplicableRule("∀x(Ax ^ (By & Ax))", "0");
        testNotApplicable("∃x(Ax ^ (Bx & Ax))");
        testNotApplicable("∀((Ax & Bx = (Bx & Ax))");
    }

    private void testNotApplicable(String proposition) {
        Rule rule = new UniversalInstantiationRule(ruleUtilities, constantList);
        assertFalse(rule.applicable(new PropositionalClassicalNode(proposition)));
    }

    private void testApplicableRule(String proposition, String expectedConstant) {
        Rule rule = new UniversalInstantiationRule(ruleUtilities, constantList);
        assertTrue(rule.applicable(new PropositionalClassicalNode(proposition)));

        List<Node> results = rule.apply();
        assertEquals(proposition.substring(2).replace("x", expectedConstant), results.get(0).getProposition());
    }
}
