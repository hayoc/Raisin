package hayoc.raisin.predicate.classical.rules;

import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.common.search.Node;

import java.util.List;

/**
 * Created by Hayo on 04/01/2017.
 */
public class NegatedExistentialInstantiationRule implements Rule {

    private PredicateClassicalRuleUtilities ruleUtilities;

    public NegatedExistentialInstantiationRule(PredicateClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        return false;
    }

    @Override
    public List<Node> apply() {
        return null;
    }
}