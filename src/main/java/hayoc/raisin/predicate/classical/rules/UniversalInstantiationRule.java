package hayoc.raisin.predicate.classical.rules;

import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.predicate.classical.common.ConstantList;
import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hayo on 04/01/2017.
 */
public class UniversalInstantiationRule implements Rule {

    private PredicateClassicalRuleUtilities ruleUtilities;
    private ConstantList constantList;

    private Node node;

    public UniversalInstantiationRule(PredicateClassicalRuleUtilities ruleUtilities, ConstantList constantList) {
        this.ruleUtilities = ruleUtilities;
        this.constantList = constantList;
    }

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;
        return proposition.getProposition().charAt(0) == AbstractRuleUtilities.UNIVERSAL_QUANTIFIER;
    }

    @Override
    public List<Node> apply() {
        String proposition = node.getProposition();
        String variable = String.valueOf(proposition.charAt(1));
        String constant = constantList.getExistingOrNewConstant();
        proposition = proposition.substring(2).replace(variable, constant);
        return Collections.singletonList(new PropositionalClassicalNode(proposition, node, null));
    }
}