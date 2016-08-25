package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.rules.Rule;

import java.util.List;

/**
 * Created by Hayo on 25/08/2016.
 */
public class ConjunctionRule implements Rule {

    private PropositionalModalRuleUtilities ruleUtilities;

    private Node node;
    private int splitPosition;

    public ConjunctionRule(PropositionalModalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION)
            return false;

        splitPosition = ruleUtilities.getConnectivePosition(proposition, AbstractRuleUtilities.CONJUNCTION);

        return splitPosition != 0;
    }

    @Override
    public List<Node> apply() {
        return null;
    }
}
