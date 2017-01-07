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
public class UniversalInstantiationRule implements Rule {

    private static final Logger LOG = Logger.getLogger(UniversalInstantiationRule.class);

    private PredicateClassicalRuleUtilities ruleUtilities;
    private VariableConstantMap variableConstantMap;

    private Node node;

    public UniversalInstantiationRule(PredicateClassicalRuleUtilities ruleUtilities, VariableConstantMap variableConstantMap) {
        this.ruleUtilities = ruleUtilities;
        this.variableConstantMap = variableConstantMap;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;
        return proposition.getProposition().charAt(0) == AbstractRuleUtilities.UNIVERSAL_QUANTIFIER && Character.isLowerCase(proposition.getProposition().charAt(1));
    }

    @Override
    public List<Node> apply() {
        String proposition = node.getProposition();
        String variable = String.valueOf(proposition.charAt(1));
        String constant = variableConstantMap.getExistingOrNewConstant(variable);
        List<Node> result = ruleUtilities.createSingleChild(proposition.substring(2).replace(variable, constant), node);

        LOG.debug(node.toString() + " ==> " + result.toString());
        return result;
    }
}