package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractImplicationRule;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.modal.ModalUtilities;

import java.util.List;

/**
 * Created by Hayo on 25/08/2016.
 */
public class ImplicationRule extends AbstractImplicationRule {

    public ImplicationRule(PropositionalModalRuleUtilities ruleUtilities) {
        super(ruleUtilities);
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION || ModalUtilities.isModal(node))
            return false;

        splitPosition = ruleUtilities.getConnectivePosition(proposition, AbstractRuleUtilities.CONDITIONAL);

        return splitPosition != 0;
    }

    @Override
    public List<Node> apply() {
        String nonmodalProposition = ModalUtilities.getNonModalProposition(node);

        String antecedent = AbstractRuleUtilities.NEGATION + nonmodalProposition.substring(1, splitPosition).trim() + ModalUtilities.writeWorld(ModalUtilities.getWorld(node));
        String consequent = nonmodalProposition.substring(splitPosition + 1, nonmodalProposition.length() - 1).trim() + ModalUtilities.writeWorld(ModalUtilities.getWorld(node));

        return ruleUtilities.createSeparateBranchChildren(node, antecedent, consequent);
    }
}
