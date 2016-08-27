package hayoc.raisin.propositional.common.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.modal.rules.PropositionalModalRuleUtilities;
import org.apache.commons.collections4.ListUtils;

import java.util.List;

/**
 * Created by Hayo on 25/08/2016.
 */
public abstract class AbstractBiconditionalRule implements Rule {

    protected RuleUtilities ruleUtilities;

    protected Node node;
    protected int splitPosition;

    public AbstractBiconditionalRule(RuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION)
            return false;

        splitPosition = ruleUtilities.getConnectivePosition(proposition, AbstractRuleUtilities.BICONDITIONAL);

        return splitPosition != 0;
    }

    @Override
    public List<Node> apply() {
        String firstAntecedent = node.getProposition().substring(1, splitPosition).trim();
        String firstConsequent = node.getProposition().substring(splitPosition + 1, node.getProposition().length() - 1).trim();
        String secondAntecedent = AbstractRuleUtilities.NEGATION + node.getProposition().substring(1, splitPosition).trim();
        String secondConsequent = AbstractRuleUtilities.NEGATION + node.getProposition().substring(splitPosition + 1, node.getProposition().length() - 1).trim();

        List<Node> firstSet = ruleUtilities.createSameBranchChildren(node, firstAntecedent, firstConsequent);
        List<Node> secondSet = ruleUtilities.createSameBranchChildren(node, secondAntecedent, secondConsequent);

        return ListUtils.union(firstSet, secondSet);
    }
}
