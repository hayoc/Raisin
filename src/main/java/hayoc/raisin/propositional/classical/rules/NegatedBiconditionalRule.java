package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.PropositionalUtilities;
import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import org.apache.commons.collections4.ListUtils;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class NegatedBiconditionalRule implements PropositionalClassicalRule {

    private PropositionalClassicalRuleUtilities ruleUtilities;

    private PropositionalClassicalNode node;
    private int splitPosition;

    public NegatedBiconditionalRule(PropositionalClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(PropositionalClassicalNode proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) != PropositionalUtilities.NEGATION || proposition.getProposition().charAt(1) == PropositionalUtilities.NEGATION)
            return false;

        splitPosition = ruleUtilities.getConnectivePosition(proposition, PropositionalUtilities.BICONDITIONAL);

        return splitPosition != 0;
    }

    @Override
    public List<PropositionalClassicalNode> apply() {
        String firstAntecedent = node.getProposition().substring(2, splitPosition).trim();
        String firstConsequent = PropositionalUtilities.NEGATION + node.getProposition().substring(splitPosition + 1, node.getProposition().length() - 1).trim();
        String secondAntecedent = PropositionalUtilities.NEGATION + node.getProposition().substring(2, splitPosition).trim();
        String secondConsequent = node.getProposition().substring(splitPosition + 1, node.getProposition().length() - 1).trim();

        List<PropositionalClassicalNode> firstSet = ruleUtilities.createSameBranchChildren(node, firstAntecedent, firstConsequent);
        List<PropositionalClassicalNode> secondSet = ruleUtilities.createSameBranchChildren(node, secondAntecedent, secondConsequent);

        return ListUtils.union(firstSet, secondSet);
    }
}
