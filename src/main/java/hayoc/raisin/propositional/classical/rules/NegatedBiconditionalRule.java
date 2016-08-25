package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.rules.Rule;
import org.apache.commons.collections4.ListUtils;
import hayoc.raisin.propositional.common.Node;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class NegatedBiconditionalRule implements Rule {

    private PropositionalClassicalRuleUtilities ruleUtilities;

    private Node node;
    private int splitPosition;

    public NegatedBiconditionalRule(PropositionalClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) != AbstractRuleUtilities.NEGATION || proposition.getProposition().charAt(1) == AbstractRuleUtilities.NEGATION)
            return false;

        splitPosition = ruleUtilities.getConnectivePosition(proposition, AbstractRuleUtilities.BICONDITIONAL);

        return splitPosition != 0;
    }

    @Override
    public List<Node> apply() {
        String firstAntecedent = node.getProposition().substring(2, splitPosition).trim();
        String firstConsequent = AbstractRuleUtilities.NEGATION + node.getProposition().substring(splitPosition + 1, node.getProposition().length() - 1).trim();
        String secondAntecedent = AbstractRuleUtilities.NEGATION + node.getProposition().substring(2, splitPosition).trim();
        String secondConsequent = node.getProposition().substring(splitPosition + 1, node.getProposition().length() - 1).trim();

        List<Node> firstSet = ruleUtilities.createSameBranchChildren(node, firstAntecedent, firstConsequent);
        List<Node> secondSet = ruleUtilities.createSameBranchChildren(node, secondAntecedent, secondConsequent);

        return ListUtils.union(firstSet, secondSet);
    }
}
