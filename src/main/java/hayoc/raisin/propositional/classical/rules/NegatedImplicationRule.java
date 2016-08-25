package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractNegatedImplicationRule;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.common.rules.RuleUtilities;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class NegatedImplicationRule extends AbstractNegatedImplicationRule{

    public NegatedImplicationRule(PropositionalClassicalRuleUtilities ruleUtilities) {
        super(ruleUtilities);
    }
}
