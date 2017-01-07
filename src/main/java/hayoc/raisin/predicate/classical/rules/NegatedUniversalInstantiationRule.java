package hayoc.raisin.predicate.classical.rules;

import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.predicate.classical.common.VariableConstantMap;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Hayo on 04/01/2017.
 */
public class NegatedUniversalInstantiationRule implements Rule {

    private static final Logger LOG = Logger.getLogger(NegatedUniversalInstantiationRule.class);

    private PredicateClassicalRuleUtilities ruleUtilities;
    private VariableConstantMap variableConstantMap;

    private Node node;

    public NegatedUniversalInstantiationRule(PredicateClassicalRuleUtilities ruleUtilities, VariableConstantMap variableConstantMap) {
        this.ruleUtilities = ruleUtilities;
        this.variableConstantMap = variableConstantMap;
    }
    public NegatedUniversalInstantiationRule(PredicateClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;
        return proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION &&
                proposition.getProposition().charAt(1) == AbstractRuleUtilities.UNIVERSAL_QUANTIFIER && Character.isLowerCase(proposition.getProposition().charAt(2));

    }

    @Override
    public List<Node> apply() {
        String proposition = node.getProposition();
        proposition = AbstractRuleUtilities.EXISTENTIAL_QUANTIFIER + proposition.substring(2,3) + AbstractRuleUtilities.NEGATION + proposition.substring(3);
        List<Node> result = ruleUtilities.createSingleChild(proposition, node);

        LOG.debug(node.toString() + " ==> " + result.toString());
        return result;
    }
}