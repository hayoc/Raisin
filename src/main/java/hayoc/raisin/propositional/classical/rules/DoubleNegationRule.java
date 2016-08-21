package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.PropositionalUtilities;
import hayoc.raisin.search.Node;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class DoubleNegationRule implements PropositionalClassicalRule {

    private PropositionalClassicalRuleUtilities ruleUtilities;

    private Node node;

    public DoubleNegationRule(PropositionalClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        return proposition.getProposition().charAt(0) == PropositionalUtilities.NEGATION && proposition.getProposition().charAt(1) == PropositionalUtilities.NEGATION;
    }

    @Override
    public List<Node> apply() {
        Node result = new Node(node.getProposition().substring(2).trim(), node, null);
        return Collections.singletonList(result);
    }
}
