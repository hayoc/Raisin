package hayoc.raisin.common.rules;

import hayoc.raisin.common.search.Node;
import org.apache.commons.collections4.ListUtils;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Hayo on 25/08/2016.
 */
public class AbstractNegatedBiconditionalRule implements Rule {

    private static final Logger LOG = Logger.getLogger(AbstractNegatedBiconditionalRule.class);

    protected RuleUtilities ruleUtilities;

    protected Node node;
    protected int splitPosition;

    public AbstractNegatedBiconditionalRule(RuleUtilities ruleUtilities) {
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
        List<Node> result = ListUtils.union(firstSet, secondSet);

        LOG.debug(node.toString() + " ==> " + result.toString());
        return result;
    }
}
