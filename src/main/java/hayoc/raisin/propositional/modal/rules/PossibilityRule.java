package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.modal.search.PropositionalModalNode;
import hayoc.raisin.propositional.modal.search.WorldNode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class PossibilityRule implements Rule {

    private PropositionalModalRuleUtilities ruleUtilities;

    private Node proposition;
    private WorldNode relativeWorld;

    public PossibilityRule(PropositionalModalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.proposition = proposition;

        if (proposition.getProposition().charAt(0) != AbstractRuleUtilities.POSSIBILITY)
            return false;

        PropositionalModalNode modalNode = (PropositionalModalNode) proposition;
        relativeWorld = ruleUtilities.setRelativeWorld(modalNode.getWorld());

        return true;
    }

    @Override
    public List<Node> apply() {
        return Collections.singletonList(new PropositionalModalNode(proposition.getProposition(), proposition, null, relativeWorld));
    }
}
