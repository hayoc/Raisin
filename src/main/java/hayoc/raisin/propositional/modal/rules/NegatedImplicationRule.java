package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.rules.AbstractNegatedImplicationRule;
import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.modal.ModalUtilities;

import java.util.List;

/**
 * Created by Hayo on 25/08/2016.
 */
public class NegatedImplicationRule  extends AbstractNegatedImplicationRule {
    
    private ModalUtilities modalUtilities;

    public NegatedImplicationRule(PropositionalModalRuleUtilities ruleUtilities, ModalUtilities modalUtilities) {
        super(ruleUtilities);
        this.modalUtilities = modalUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) != AbstractRuleUtilities.NEGATION || proposition.getProposition().charAt(1) == AbstractRuleUtilities.NEGATION &&
                modalUtilities.isModal(node))
            return false;

        splitPosition = ruleUtilities.getConnectivePosition(proposition, AbstractRuleUtilities.CONDITIONAL);

        return splitPosition != 0;
    }

    @Override
    public List<Node> apply() {
        String nonmodalProposition = modalUtilities.getNonModalProposition(node);

        String antecedent = nonmodalProposition.substring(2, splitPosition).trim() + modalUtilities.writeWorld(modalUtilities.getWorld(node));
        String consequent = AbstractRuleUtilities.NEGATION + nonmodalProposition.substring(splitPosition + 1, nonmodalProposition.length() - 1).trim() + modalUtilities.writeWorld(modalUtilities.getWorld(node));

        return ruleUtilities.createSameBranchChildren(node, antecedent, consequent);
    }
}
