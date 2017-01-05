package hayoc.raisin.predicate.classical.rules;

import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.predicate.classical.common.ConstantList;
import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hayo on 04/01/2017.
 */
public class NegatedExistentialInstantiationRule implements Rule {

    private static final Logger LOG = Logger.getLogger(NegatedExistentialInstantiationRule.class);

    private PredicateClassicalRuleUtilities ruleUtilities;
    private ConstantList constantList;

    private Node node;

    public NegatedExistentialInstantiationRule(PredicateClassicalRuleUtilities ruleUtilities, ConstantList constantList) {
        this.ruleUtilities = ruleUtilities;
        this.constantList = constantList;
    }
    public NegatedExistentialInstantiationRule(PredicateClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;
        return proposition.getProposition().charAt(0) == AbstractRuleUtilities.NEGATION &&
                proposition.getProposition().charAt(1) == AbstractRuleUtilities.EXISTENTIAL_QUANTIFIER && Character.isLowerCase(proposition.getProposition().charAt(2));

    }

    @Override
    public List<Node> apply() {
        String proposition = node.getProposition();
        proposition = AbstractRuleUtilities.UNIVERSAL_QUANTIFIER + proposition.substring(2,3) + AbstractRuleUtilities.NEGATION + proposition.substring(3);
        Node result = new PropositionalClassicalNode(proposition, node, null);

        LOG.debug(node.toString() + " ==> " + result.toString());
        return Collections.singletonList(result);
    }
}