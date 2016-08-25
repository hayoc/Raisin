package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.Rule;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class ImplicationRule implements Rule {

    private PropositionalClassicalRuleUtilities ruleUtilities;

    private Node node;
    private int splitPosition;

    public ImplicationRule(PropositionalClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION)
            return false;

        splitPosition = ruleUtilities.getConnectivePosition(proposition, AbstractRuleUtilities.CONDITIONAL);

        return splitPosition != 0;
    }

    @Override
    public List<Node> apply() {
        String antecedent = AbstractRuleUtilities.NEGATION + node.getProposition().substring(1, splitPosition).trim();
        String consequent = node.getProposition().substring(splitPosition + 1, node.getProposition().length() - 1).trim();

        return ruleUtilities.createSeparateBranchChildren(node, antecedent, consequent);
    }
}
