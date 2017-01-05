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
public class ExistentialInstantiationRule implements Rule {

    private static final Logger LOG = Logger.getLogger(ExistentialInstantiationRule.class);

    private PredicateClassicalRuleUtilities ruleUtilities;
    private ConstantList constantList;

    private Node node;

    public ExistentialInstantiationRule(PredicateClassicalRuleUtilities ruleUtilities, ConstantList constantList) {
        this.ruleUtilities = ruleUtilities;
        this.constantList = constantList;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;
        return proposition.getProposition().charAt(0) == AbstractRuleUtilities.EXISTENTIAL_QUANTIFIER && Character.isLowerCase(proposition.getProposition().charAt(1));
    }

    @Override
    public List<Node> apply() {
        String proposition = node.getProposition();
        String variable = String.valueOf(proposition.charAt(1));
        String constant = constantList.getUniqueConstant();
        Node result = new PropositionalClassicalNode(proposition.substring(2).replace(variable, constant), node, null);

        LOG.debug(node.toString() + " ==> " + result.toString());
        return Collections.singletonList(result);
    }
}
