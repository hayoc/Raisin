package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.PropositionalUtilities;
import hayoc.raisin.search.Node;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class NegatedImplicationRule implements PropositionalClassicalRule {

    private PropositionalClassicalRuleUtilities ruleUtilities;

    private Node node;
    private int splitPosition;

    public NegatedImplicationRule(PropositionalClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) != PropositionalUtilities.NEGATION)
            return false;

        splitPosition = ruleUtilities.getConnectivePosition(proposition, PropositionalUtilities.CONDITIONAL);

        return splitPosition != 0;
    }

    @Override
    public List<Node> apply() {
        String antecedent = node.getProposition().substring(2, splitPosition).trim();
        String consequent = PropositionalUtilities.NEGATION + node.getProposition().substring(splitPosition + 1, node.getProposition().length() - 1).trim();

        return ruleUtilities.createSameBranchChildren(node, antecedent, consequent);
    }
}
