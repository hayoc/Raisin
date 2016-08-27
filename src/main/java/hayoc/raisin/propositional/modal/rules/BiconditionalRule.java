package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractBiconditionalRule;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.modal.ModalUtilities;
import hayoc.raisin.propositional.modal.search.PropositionalModalNode;
import hayoc.raisin.propositional.modal.search.WorldNode;
import org.apache.commons.collections4.ListUtils;

import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class BiconditionalRule extends AbstractBiconditionalRule {

    private PropositionalModalNode modalNode;

    public BiconditionalRule(PropositionalModalRuleUtilities ruleUtilities) {
        super(ruleUtilities);
    }

    @Override
    public boolean applicable(Node proposition) {
        if (proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION)
            return false;

        modalNode = (PropositionalModalNode) proposition;
        modalNode.setWorld(ModalUtilities.getWorld(modalNode));

        splitPosition = ruleUtilities.getConnectivePosition(proposition, AbstractRuleUtilities.BICONDITIONAL);

        return splitPosition != 0;
    }

    @Override
    public List<Node> apply() {
        String nonmodalProposition = ModalUtilities.getNonModalProposition(modalNode);

        String firstAntecedent = nonmodalProposition.substring(1, splitPosition).trim() + ModalUtilities.addWorld(modalNode.getWorld().getWorld());
        String firstConsequent = nonmodalProposition.substring(splitPosition + 1, nonmodalProposition.length() - 1).trim() + ModalUtilities.addWorld(modalNode.getWorld().getWorld());
        String secondAntecedent = AbstractRuleUtilities.NEGATION + nonmodalProposition.substring(1, splitPosition).trim() + ModalUtilities.addWorld(modalNode.getWorld().getWorld());
        String secondConsequent = AbstractRuleUtilities.NEGATION + nonmodalProposition.substring(splitPosition + 1, nonmodalProposition.length() - 1).trim() + ModalUtilities.addWorld(modalNode.getWorld().getWorld());

        List<Node> firstSet = ruleUtilities.createSameBranchChildren(modalNode, firstAntecedent, firstConsequent);
        List<Node> secondSet = ruleUtilities.createSameBranchChildren(modalNode, secondAntecedent, secondConsequent);

        return ListUtils.union(firstSet, secondSet);
    }
}
