package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.search.Node;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class BiconditionalRule implements PropositionalClassicalRule {

    private PropositionalClassicalRuleUtilities ruleUtilities;

    private Node node;
    private int splitPosition;

    public BiconditionalRule(PropositionalClassicalRuleUtilities ruleUtilities) {
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
