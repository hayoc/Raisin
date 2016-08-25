package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.modal.search.PropositionalModalNode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hayo on 25/08/2016.
 */
public class DoubleNegationRule implements Rule {

    private PropositionalModalRuleUtilities ruleUtilities;

    private Node node;

    public DoubleNegationRule(PropositionalModalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;
        return proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION && proposition.getProposition().charAt(1) == AbstractRuleUtilities.NEGATION;
    }

    @Override
    public List<Node> apply() {
        PropositionalModalNode modalNode = (PropositionalModalNode) node;
        Node result = new PropositionalModalNode(node.getProposition().substring(2).trim(), node, null, modalNode.getWorld());
        return Collections.singletonList(result);
    }
}
