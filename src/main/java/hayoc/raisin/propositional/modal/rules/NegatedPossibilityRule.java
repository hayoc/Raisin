package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.propositional.modal.ModalUtilities;
import hayoc.raisin.propositional.modal.search.PropositionalModalNode;

import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class NegatedPossibilityRule implements Rule {

    private PropositionalModalRuleUtilities ruleUtilities;

    private PropositionalModalNode node;

    public NegatedPossibilityRule(PropositionalModalRuleUtilities ruleUtilities, ModalUtilities modalUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node =  (PropositionalModalNode) proposition;
        return proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION && proposition.getProposition().charAt(1) == AbstractRuleUtilities.POSSIBILITY;
    }

    @Override
    public List<Node> apply() {
        return ruleUtilities.createSingleChild(node, Character.toString(AbstractRuleUtilities.NECESSITY)
                + Character.toString(AbstractRuleUtilities.NEGATION) + node.getProposition().substring(2));
    }
}
