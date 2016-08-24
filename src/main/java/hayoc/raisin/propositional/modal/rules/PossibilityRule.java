package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.common.PropositionalUtilities;
import hayoc.raisin.propositional.modal.search.PropositionalModalNode;
import hayoc.raisin.propositional.modal.search.WorldNode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class PossibilityRule implements PropositionalModalRule {

    private PropositionalModalRuleUtilities ruleUtilities;

    private PropositionalModalNode proposition;
    private WorldNode relativeWorld;

    public PossibilityRule(PropositionalModalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(PropositionalModalNode proposition) {
        this.proposition = proposition;

        if (proposition.getProposition().charAt(0) != PropositionalUtilities.POSSIBILITY)
            return false;

        relativeWorld = ruleUtilities.setRelativeWorld(proposition.getWorld());

        return true;
    }

    @Override
    public List<PropositionalModalNode> apply() {
        return Collections.singletonList(new PropositionalModalNode(proposition.getProposition(), proposition, null, relativeWorld));
    }
}
