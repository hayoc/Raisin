package hayoc.raisin.predicate.classical.rules;

import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.predicate.classical.common.ConstantList;
import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by id094697 on 05/01/2017.
 */
public class ExistentialInstantiationRuleTest {

    private PredicateClassicalRuleUtilities ruleUtilities = new PredicateClassicalRuleUtilities();
    private ConstantList constantList = new ConstantList();

    @Test
    public void testExistentialInstantiationRuleTest() {
        testApplicableRule("∃x((Ax & Bx) = (Bx & Ax))", "0");
        testApplicableRule("∃x(Ax ^ (Bx & Ax))", "1");
        testApplicableRule("∃x(Ax ^ (By & Ax))", "2");
        //testRule("∀x(Ax ^ (Bx & Ax))", "1");
    }

    private void testApplicableRule(String proposition, String expectedConstant) {
        Rule rule = new ExistentialInstantiationRule(ruleUtilities, constantList);
        assertTrue(rule.applicable(new PropositionalClassicalNode(proposition)));

        List<Node> results = rule.apply();
        assertEquals(proposition.substring(2).replace("x", expectedConstant), results.get(0).getProposition());
    }
}
