package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.modal.ModalUtilities;
import hayoc.raisin.propositional.modal.search.PropositionalModalNode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class PossibilityRule implements Rule {

    private PropositionalModalRuleUtilities ruleUtilities;

    private Node node;
    private int relativeWorld;

    public PossibilityRule(PropositionalModalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) != AbstractRuleUtilities.POSSIBILITY)
            return false;

        int world = ModalUtilities.getWorld(proposition);
        relativeWorld = world + 1;
        ModalUtilities.addRelativeWorld(world, relativeWorld);

        return true;
    }

    @Override
    public List<Node> apply() {
        return ruleUtilities.createSingleChild(node, ModalUtilities.getNonModalProposition(node).substring(1) + ModalUtilities.writeWorld(relativeWorld));
    }
}
