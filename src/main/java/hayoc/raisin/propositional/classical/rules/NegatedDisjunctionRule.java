package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.rules.AbstractNegatedDisjunctionRule;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.common.rules.RuleUtilities;
import hayoc.raisin.propositional.modal.rules.PropositionalModalRuleUtilities;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class NegatedDisjunctionRule extends AbstractNegatedDisjunctionRule {

    public NegatedDisjunctionRule(PropositionalClassicalRuleUtilities ruleUtilities) {
        super(ruleUtilities);
    }
}
