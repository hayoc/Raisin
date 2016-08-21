package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.PropositionalUtilities;
import hayoc.raisin.search.Node;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Hayo on 18/08/2016.
 */
public class PropositionalClassicalRuleUtilities {

    public static final Class[] PROPOSITIONAL_CLASSICAL_RULES = {BiconditionalRule.class, ConjunctionRule.class, DisjunctionRule.class, DoubleNegationRule.class,
                                                                    ImplicationRule.class, NegatedBiconditionalRule.class, NegatedConjunctionRule.class,
                                                                    NegatedDisjunctionRule.class, NegatedImplicationRule.class};


    public boolean branchClosed(Node proposition) {
        if (proposition.isBranchChecked())
            return proposition.isClosed();
        Node parent = proposition.getParent();
        while (parent != null) {
            if (isNegation(proposition, parent)) {
                proposition.setClosed(true);
                return true;
            }
            parent = parent.getParent();
        }
        proposition.setClosed(false);
        return false;
    }

    protected int getConnectivePosition(Node proposition, char connective) {
        int parentheses = 0;
        for (int i = 0; i < proposition.getProposition().length(); i++) {
            char c = proposition.getProposition().charAt(i);
            if (c == PropositionalUtilities.OPEN_PARENTHESIS)
                parentheses++;
            if (c == PropositionalUtilities.CLOSE_PARENTHESIS)
                parentheses--;

            if (parentheses == 1 && c == connective)
                return i;
        }

        return 0;
    }

    protected List<Node> createSameBranchChildren(Node parent, String antecedent, String consequent) {
        List<Node> nodes = new ArrayList<>();

        List<Node> childNodes = new ArrayList<>();
        getLowestChildNodes(parent, childNodes);
        for (Node node : childNodes) {
            if (branchClosed(node))
                continue;
            nodes.clear();
            List<Node> newChildren = new ArrayList<>();
            Node consequentNode = new Node(consequent, null, null);
            newChildren.add(consequentNode);

            Node antecedentNode = new Node(antecedent, node, newChildren);
            consequentNode.setParent(antecedentNode);
            nodes.add(antecedentNode);
            node.setChildren(nodes);
        }

        return nodes;
    }

    protected List<Node> createSeparateBranchChildren(Node parent, String antecedent, String consequent) {
        List<Node> nodes = new ArrayList<>();

        List<Node> childNodes = new ArrayList<>();
        getLowestChildNodes(parent, childNodes);
        for (Node node : childNodes) {
            if (branchClosed(node))
                continue;
            nodes.clear();
            nodes.add(new Node(antecedent, node, null));
            nodes.add(new Node(consequent, node, null));
            node.setChildren(nodes);
        }

        return nodes;
    }


    protected List<Node> createSeparateAndSameBranchChildren(Node parent, String firstAntecedent, String firstConsequent, String secondAntecedent, String secondConsequent) {
        List<Node> nodes = new ArrayList<>();

        List<Node> childNodes = new ArrayList<>();
        getLowestChildNodes(parent, childNodes);
        for (Node node : childNodes) {
            if (branchClosed(node))
                continue;
            nodes.clear();

            Node firstConsequentNode = new Node(firstConsequent, null, null);
            Node firstAntecedentNode = new Node(firstAntecedent, null, Collections.singletonList(firstConsequentNode));
            firstConsequentNode.setParent(firstAntecedentNode);

            Node secondConsequentNode = new Node(secondConsequent, null, null);
            Node secondAntecedentNode = new Node(secondAntecedent, null, Collections.singletonList(secondConsequentNode));
            secondConsequentNode.setParent(secondAntecedentNode);

            nodes.add(firstAntecedentNode);
            nodes.add(secondAntecedentNode);
            node.setChildren(nodes);
        }

        return nodes;
    }

    protected boolean isNegation(Node propositionNode, Node parentNode) {
        String proposition = propositionNode.getProposition();
        String parent = parentNode.getProposition();
        if (proposition.charAt(0) == PropositionalUtilities.NEGATION) {
            return proposition.substring(1).equals(parent);
        } else {
            return proposition.equals(parent.substring(1));
        }
    }

    protected void getLowestChildNodes(Node node, List<Node> childNodes) {
        if (CollectionUtils.isEmpty(node.getChildren())) {
            childNodes.add(node);
        } else {
            for (Node child : node.getChildren()) {
                getLowestChildNodes(child, childNodes);
            }
        }
    }
}
