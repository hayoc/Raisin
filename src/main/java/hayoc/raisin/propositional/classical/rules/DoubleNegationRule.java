package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.rules.Rule;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class DoubleNegationRule implements Rule {

    private PropositionalClassicalRuleUtilities ruleUtilities;

    private Node node;

    public DoubleNegationRule(PropositionalClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        return proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION && proposition.getProposition().charAt(1) == AbstractRuleUtilities.NEGATION;
    }

    @Override
    public List<Node> apply() {
        Node result = new PropositionalClassicalNode(node.getProposition().substring(2).trim(), node, null);
        return Collections.singletonList(result);
    }
}
