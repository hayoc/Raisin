package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.propositional.modal.ModalUtilities;
import hayoc.raisin.propositional.modal.search.PropositionalModalNode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hayo on 25/08/2016.
 */
public class DoubleNegationRule implements Rule {

    private PropositionalModalRuleUtilities ruleUtilities;
    private ModalUtilities modalUtilities;

    private Node node;

    public DoubleNegationRule(PropositionalModalRuleUtilities ruleUtilities, ModalUtilities modalUtilities) {
        this.ruleUtilities = ruleUtilities;
        this.modalUtilities = modalUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;
        return proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION && proposition.getProposition().charAt(1) == AbstractRuleUtilities.NEGATION;
    }

    @Override
    public List<Node> apply() {
        Node result = new PropositionalModalNode(node.getProposition().substring(2).trim(), node, null);
        return Collections.singletonList(result);
    }
}
