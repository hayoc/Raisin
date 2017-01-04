package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.common.Node;
import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.propositional.modal.ModalUtilities;

import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class PossibilityRule implements Rule {

    private PropositionalModalRuleUtilities ruleUtilities;
    private ModalUtilities modalUtilities;
    
    private Node node;
    private int relativeWorld;

    public PossibilityRule(PropositionalModalRuleUtilities ruleUtilities, ModalUtilities modalUtilities) {
        this.ruleUtilities = ruleUtilities;
        this.modalUtilities = modalUtilities;

    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) != AbstractRuleUtilities.POSSIBILITY)
            return false;

        int world = modalUtilities.getWorld(proposition);
        relativeWorld = world + 1;
        modalUtilities.addRelativeWorld(world, relativeWorld);

        return true;
    }

    @Override
    public List<Node> apply() {
        return ruleUtilities.createSingleChild(node, modalUtilities.getNonModalProposition(node).substring(1) + modalUtilities.writeWorld(relativeWorld));
    }
}
