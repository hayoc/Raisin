package hayoc.raisin.predicate.classical.rules;

import hayoc.raisin.common.rules.AbstractConjunctionRule;
import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.predicate.classical.common.VariableConstantMap;

/**
 * Created by Hayo on 07/01/2017.
 */
public class ConjunctionRule extends AbstractConjunctionRule{

    public ConjunctionRule(PredicateClassicalRuleUtilities ruleUtilities, VariableConstantMap variableConstantMap) {
        super(ruleUtilities);
    }

    @Override
    public boolean applicable(Node proposition) {
        if (proposition.getProposition().charAt(0) == AbstractRuleUtilities.UNIVERSAL_QUANTIFIER || proposition.getProposition().charAt(0) == AbstractRuleUtilities.EXISTENTIAL_QUANTIFIER)
            return false;
        return super.applicable(proposition);
    }
}
