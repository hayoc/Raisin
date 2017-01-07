package hayoc.raisin.common.rules;

import hayoc.raisin.common.search.Node;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Hayo on 07/01/2017.
 */
public abstract class AbstractDoubleNegationRule implements Rule {

    private static final Logger LOG = Logger.getLogger(AbstractDoubleNegationRule.class);

    private RuleUtilities ruleUtilities;

    private Node node;

    public AbstractDoubleNegationRule(RuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;
        return proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION && proposition.getProposition().charAt(1) == AbstractRuleUtilities.NEGATION;
    }

    @Override
    public List<Node> apply() {
        List<Node> result = ruleUtilities.createSingleChild(node.getProposition().substring(2).trim(), node);

        LOG.debug(node.toString() + " ==> " + result.toString());
        return result;
    }
}
