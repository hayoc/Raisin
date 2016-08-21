package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.search.Node;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class DoubleNegationRule implements PropositionalClassicalRule {

    private PropositionalClassicalRuleUtilities ruleUtilities;

    private Node node;
    private int splitPosition;

    public DoubleNegationRule(PropositionalClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        return false;
    }

    @Override
    public List<Node> apply() {
        return null;
    }
}
