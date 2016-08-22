package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.PropositionalUtilities;
import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class ImplicationRule implements PropositionalClassicalRule {

    private PropositionalClassicalRuleUtilities ruleUtilities;

    private PropositionalClassicalNode node;
    private int splitPosition;

    public ImplicationRule(PropositionalClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(PropositionalClassicalNode proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) == PropositionalUtilities.NEGATION)
            return false;

        splitPosition = ruleUtilities.getConnectivePosition(proposition, PropositionalUtilities.CONDITIONAL);

        return splitPosition != 0;
    }

    @Override
    public List<PropositionalClassicalNode> apply() {
        String antecedent = PropositionalUtilities.NEGATION + node.getProposition().substring(1, splitPosition).trim();
        String consequent = node.getProposition().substring(splitPosition + 1, node.getProposition().length() - 1).trim();

        return ruleUtilities.createSeparateBranchChildren(node, antecedent, consequent);
    }
}
