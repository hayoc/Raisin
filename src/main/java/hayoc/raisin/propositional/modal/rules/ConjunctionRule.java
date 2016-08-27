package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractConjunctionRule;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.common.rules.RuleUtilities;
import hayoc.raisin.propositional.modal.ModalUtilities;
import hayoc.raisin.propositional.modal.search.PropositionalModalNode;

import java.util.List;

/**
 * Created by Hayo on 25/08/2016.
 */
public class ConjunctionRule extends AbstractConjunctionRule {

    private PropositionalModalNode modalNode;

    public ConjunctionRule(PropositionalModalRuleUtilities ruleUtilities) {
        super(ruleUtilities);
    }

    @Override
    public boolean applicable(Node proposition) {
        if (proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION)
            return false;

        modalNode = (PropositionalModalNode) proposition;
        modalNode.setWorld(ModalUtilities.getWorld(modalNode));

        splitPosition = ruleUtilities.getConnectivePosition(proposition, AbstractRuleUtilities.CONJUNCTION);

        return splitPosition != 0;
    }

    @Override
    public List<Node> apply() {
        String nonmodalProposition = ModalUtilities.getNonModalProposition(modalNode);

        String antecedent = nonmodalProposition.substring(1, splitPosition).trim() + ModalUtilities.addWorld(modalNode.getWorld().getWorld());
        String consequent = nonmodalProposition.substring(splitPosition + 1, nonmodalProposition.length() - 1).trim() + ModalUtilities.addWorld(modalNode.getWorld().getWorld());

        return ruleUtilities.createSameBranchChildren(modalNode, antecedent, consequent);
    }
}
