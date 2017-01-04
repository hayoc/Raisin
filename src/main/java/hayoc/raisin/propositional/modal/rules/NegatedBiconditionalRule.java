package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.rules.AbstractNegatedBiconditionalRule;
import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.modal.ModalUtilities;
import org.apache.commons.collections4.ListUtils;

import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class NegatedBiconditionalRule extends AbstractNegatedBiconditionalRule {
    
    private ModalUtilities modalUtilities;

    public NegatedBiconditionalRule(PropositionalModalRuleUtilities ruleUtilities, ModalUtilities modalUtilities) {
        super(ruleUtilities);
        this.modalUtilities = modalUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) != AbstractRuleUtilities.NEGATION || proposition.getProposition().charAt(1) == AbstractRuleUtilities.NEGATION &&
                modalUtilities.isModal(node))
            return false;

        splitPosition = ruleUtilities.getConnectivePosition(proposition, AbstractRuleUtilities.BICONDITIONAL);

        return splitPosition != 0;
    }

    @Override
    public List<Node> apply() {
        String nonmodalProposition = modalUtilities.getNonModalProposition(node);

        String firstAntecedent = nonmodalProposition.substring(2, splitPosition).trim() + modalUtilities.writeWorld(modalUtilities.getWorld(node));
        String firstConsequent = AbstractRuleUtilities.NEGATION + nonmodalProposition.substring(splitPosition + 1, nonmodalProposition.length() - 1).trim() + modalUtilities.writeWorld(modalUtilities.getWorld(node));
        String secondAntecedent = AbstractRuleUtilities.NEGATION + nonmodalProposition.substring(2, splitPosition).trim() + modalUtilities.writeWorld(modalUtilities.getWorld(node));
        String secondConsequent = nonmodalProposition.substring(splitPosition + 1, nonmodalProposition.length() - 1).trim() + modalUtilities.writeWorld(modalUtilities.getWorld(node));

        List<Node> firstSet = ruleUtilities.createSameBranchChildren(node, firstAntecedent, firstConsequent);
        List<Node> secondSet = ruleUtilities.createSameBranchChildren(node, secondAntecedent, secondConsequent);

        return ListUtils.union(firstSet, secondSet);
    }
}
