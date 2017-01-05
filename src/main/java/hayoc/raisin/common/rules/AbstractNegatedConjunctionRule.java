package hayoc.raisin.common.rules;

import hayoc.raisin.common.search.Node;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Hayo on 25/08/2016.
 */
public class AbstractNegatedConjunctionRule implements Rule {

    private static final Logger LOG = Logger.getLogger(AbstractNegatedConjunctionRule.class);

    protected RuleUtilities ruleUtilities;

    protected Node node;
    protected int splitPosition;

    public AbstractNegatedConjunctionRule(RuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) != AbstractRuleUtilities.NEGATION || proposition.getProposition().charAt(1) == AbstractRuleUtilities.NEGATION)
            return false;

        splitPosition = ruleUtilities.getConnectivePosition(proposition, AbstractRuleUtilities.CONJUNCTION);

        return splitPosition != 0;
    }

    @Override
    public List<Node> apply() {
        String antecedent = AbstractRuleUtilities.NEGATION + node.getProposition().substring(2, splitPosition).trim();
        String consequent = AbstractRuleUtilities.NEGATION + node.getProposition().substring(splitPosition + 1, node.getProposition().length() - 1).trim();
        List<Node> result = ruleUtilities.createSeparateBranchChildren(node, antecedent, consequent);

        LOG.debug(node.toString() + " ==> " + result.toString());
        return result;
    }
}
