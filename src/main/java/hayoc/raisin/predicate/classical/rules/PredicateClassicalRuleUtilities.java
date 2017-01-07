package hayoc.raisin.predicate.classical.rules;

import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.predicate.classical.search.PredicateClassicalNode;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayo on 04/01/2017.
 */
public class PredicateClassicalRuleUtilities extends AbstractRuleUtilities {

    private static final Logger LOG = Logger.getLogger(PredicateClassicalRuleUtilities.class);

    public static final Class[] PREDICATE_CLASSICAL_RULES = {BiconditionalRule.class, ConjunctionRule.class, DisjunctionRule.class, DoubleNegationRule.class,
            ImplicationRule.class, NegatedBiconditionalRule.class, NegatedConjunctionRule.class,
            NegatedDisjunctionRule.class, NegatedImplicationRule.class, ExistentialInstantiationRule.class, UniversalInstantiationRule.class,
            NegatedExistentialInstantiationRule.class, NegatedUniversalInstantiationRule.class};

    public List<Node> createSameBranchChildren(Node parent, String antecedent, String consequent) {
        List<Node> nodes = new ArrayList<>();

        List<Node> childNodes = new ArrayList<>();
        getLowestChildNodes(parent, childNodes);
        for (Node node : childNodes) {
            if (branchClosed(node))
                continue;
            nodes.clear();
            List<Node> newChildren = new ArrayList<>();
            Node consequentNode = new PredicateClassicalNode(consequent, null, null);
            newChildren.add(consequentNode);

            Node antecedentNode = new PredicateClassicalNode(antecedent, node, newChildren);
            consequentNode.setParent(antecedentNode);
            nodes.add(antecedentNode);
            node.setChildren(nodes);
        }

        return nodes;
    }

    public List<Node> createSeparateBranchChildren(Node parent, String antecedent, String consequent) {
        List<Node> nodes = new ArrayList<>();

        List<Node> childNodes = new ArrayList<>();
        getLowestChildNodes(parent, childNodes);
        for (Node node : childNodes) {
            if (branchClosed(node))
                continue;
            nodes.clear();
            nodes.add(new PredicateClassicalNode(antecedent, node, null));
            nodes.add(new PredicateClassicalNode(consequent, node, null));
            node.setChildren(nodes);
        }

        return nodes;
    }

    @Override
    public List<Node> createSingleChild(String proposition, Node parent) {
        List<Node> nodes = new ArrayList<>();

        List<Node> childNodes = new ArrayList<>();
        getLowestChildNodes(parent, childNodes);

        for (Node node : childNodes) {
            if (branchClosed(node))
                continue;
            nodes.clear();
            nodes.add(new PredicateClassicalNode(proposition, node, null));
            node.setChildren(nodes);
        }

        return nodes;
    }
}
