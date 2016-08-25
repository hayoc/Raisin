package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.rules.AbstractBiconditionalRule;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.common.rules.RuleUtilities;
import org.apache.commons.collections4.ListUtils;
import hayoc.raisin.propositional.common.Node;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class BiconditionalRule extends AbstractBiconditionalRule {

    public BiconditionalRule(PropositionalClassicalRuleUtilities ruleUtilities) {
        super(ruleUtilities);
    }
}
