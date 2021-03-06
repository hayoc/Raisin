package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.propositional.modal.ModalUtilities;

import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class NecessityRule implements Rule {

    private PropositionalModalRuleUtilities ruleUtilities;
    private ModalUtilities modalUtilities;
    
    private Node node;
    private int relativeWorld;

    public NecessityRule(PropositionalModalRuleUtilities ruleUtilities, ModalUtilities modalUtilities) {
        this.ruleUtilities = ruleUtilities;
        this.modalUtilities = modalUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) != AbstractRuleUtilities.NECESSITY)
            return false;

        relativeWorld = modalUtilities.getRelativeWorld(node);

        return relativeWorld > 0;
    }

    @Override
    public List<Node> apply() {
        return ruleUtilities.createSingleChild(node, modalUtilities.getNonModalProposition(node).substring(1) + modalUtilities.writeWorld(relativeWorld));
    }
}
