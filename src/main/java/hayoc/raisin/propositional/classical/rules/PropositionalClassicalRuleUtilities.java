package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayo on 18/08/2016.
 */
public class PropositionalClassicalRuleUtilities extends AbstractRuleUtilities {

    public static final Class[] PROPOSITIONAL_CLASSICAL_RULES = {BiconditionalRule.class, ConjunctionRule.class, DisjunctionRule.class, DoubleNegationRule.class,
                                                                    ImplicationRule.class, NegatedBiconditionalRule.class, NegatedConjunctionRule.class,
                                                                    NegatedDisjunctionRule.class, NegatedImplicationRule.class};

    public List<Node> createSameBranchChildren(Node parent, String antecedent, String consequent) {
        List<Node> nodes = new ArrayList<>();

        List<Node> childNodes = new ArrayList<>();
        getLowestChildNodes(parent, childNodes);
        for (Node node : childNodes) {
            if (branchClosed(node))
                continue;
            nodes.clear();
            List<Node> newChildren = new ArrayList<>();
            Node consequentNode = new PropositionalClassicalNode(consequent, null, null);
            newChildren.add(consequentNode);

            Node antecedentNode = new PropositionalClassicalNode(antecedent, node, newChildren);
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
            nodes.add(new PropositionalClassicalNode(antecedent, node, null));
            nodes.add(new PropositionalClassicalNode(consequent, node, null));
            node.setChildren(nodes);
        }

        return nodes;
    }
}
