package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.modal.search.PropositionalModalNode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class NegatedNeccesityRule implements Rule {

    private PropositionalModalRuleUtilities ruleUtilities;

    private PropositionalModalNode node;

    public NegatedNeccesityRule(PropositionalModalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node =  (PropositionalModalNode) proposition;
        return proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION && proposition.getProposition().charAt(1) == AbstractRuleUtilities.NECESSITY;
    }

    @Override
    public List<Node> apply() {
        return ruleUtilities.createSingleChild(node, Character.toString(AbstractRuleUtilities.POSSIBILITY)
                + Character.toString(AbstractRuleUtilities.NEGATION) + node.getProposition().substring(2));
    }
}