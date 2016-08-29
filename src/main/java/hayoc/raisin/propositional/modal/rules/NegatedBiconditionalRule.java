package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractNegatedBiconditionalRule;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.modal.ModalUtilities;
import org.apache.commons.collections4.ListUtils;

import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class NegatedBiconditionalRule extends AbstractNegatedBiconditionalRule {

    public NegatedBiconditionalRule(PropositionalModalRuleUtilities ruleUtilities) {
        super(ruleUtilities);
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) != AbstractRuleUtilities.NEGATION || proposition.getProposition().charAt(1) == AbstractRuleUtilities.NEGATION &&
                ModalUtilities.isModal(node))
            return false;

        splitPosition = ruleUtilities.getConnectivePosition(proposition, AbstractRuleUtilities.BICONDITIONAL);

        return splitPosition != 0;
    }

    @Override
    public List<Node> apply() {
        String nonmodalProposition = ModalUtilities.getNonModalProposition(node);

        String firstAntecedent = nonmodalProposition.substring(2, splitPosition).trim() + ModalUtilities.writeWorld(ModalUtilities.getWorld(node));
        String firstConsequent = AbstractRuleUtilities.NEGATION + nonmodalProposition.substring(splitPosition + 1, nonmodalProposition.length() - 1).trim() + ModalUtilities.writeWorld(ModalUtilities.getWorld(node));
        String secondAntecedent = AbstractRuleUtilities.NEGATION + nonmodalProposition.substring(2, splitPosition).trim() + ModalUtilities.writeWorld(ModalUtilities.getWorld(node));
        String secondConsequent = nonmodalProposition.substring(splitPosition + 1, nonmodalProposition.length() - 1).trim() + ModalUtilities.writeWorld(ModalUtilities.getWorld(node));

        List<Node> firstSet = ruleUtilities.createSameBranchChildren(node, firstAntecedent, firstConsequent);
        List<Node> secondSet = ruleUtilities.createSameBranchChildren(node, secondAntecedent, secondConsequent);

        return ListUtils.union(firstSet, secondSet);
    }
}
