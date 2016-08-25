package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRuleUtilities;
import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractNegatedDisjunctionRule;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.common.rules.RuleUtilities;

import java.util.List;

/**
 * Created by Hayo on 25/08/2016.
 */
public class NegatedDisjunctionRule extends AbstractNegatedDisjunctionRule {

    public NegatedDisjunctionRule(PropositionalModalRuleUtilities ruleUtilities) {
        super(ruleUtilities);
    }
}
