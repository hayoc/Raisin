package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.PropositionalUtilities;
import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayo on 18/08/2016.
 */
public class PropositionalClassicalRuleUtilities {

    public static final Class[] PROPOSITIONAL_CLASSICAL_RULES = {BiconditionalRule.class, ConjunctionRule.class, DisjunctionRule.class, DoubleNegationRule.class,
                                                                    ImplicationRule.class, NegatedBiconditionalRule.class, NegatedConjunctionRule.class,
                                                                    NegatedDisjunctionRule.class, NegatedImplicationRule.class};


    public boolean branchClosed(PropositionalClassicalNode proposition) {
        if (proposition.isBranchChecked())
            return proposition.isClosed();
        PropositionalClassicalNode parent = proposition.getParent();
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

    protected int getConnectivePosition(PropositionalClassicalNode proposition, char connective) {
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

    protected List<PropositionalClassicalNode> createSameBranchChildren(PropositionalClassicalNode parent, String antecedent, String consequent) {
        List<PropositionalClassicalNode> nodes = new ArrayList<>();

        List<PropositionalClassicalNode> childNodes = new ArrayList<>();
        getLowestChildNodes(parent, childNodes);
        for (PropositionalClassicalNode node : childNodes) {
            if (branchClosed(node))
                continue;
            nodes.clear();
            List<PropositionalClassicalNode> newChildren = new ArrayList<>();
            PropositionalClassicalNode consequentNode = new PropositionalClassicalNode(consequent, null, null);
            newChildren.add(consequentNode);

            PropositionalClassicalNode antecedentNode = new PropositionalClassicalNode(antecedent, node, newChildren);
            consequentNode.setParent(antecedentNode);
            nodes.add(antecedentNode);
            node.setChildren(nodes);
        }

        return nodes;
    }

    protected List<PropositionalClassicalNode> createSeparateBranchChildren(PropositionalClassicalNode parent, String antecedent, String consequent) {
        List<PropositionalClassicalNode> nodes = new ArrayList<>();

        List<PropositionalClassicalNode> childNodes = new ArrayList<>();
        getLowestChildNodes(parent, childNodes);
        for (PropositionalClassicalNode node : childNodes) {
            if (branchClosed(node))
                continue;
            nodes.clear();
            nodes.add(new PropositionalClassicalNode(antecedent, node, null));
            nodes.add(new PropositionalClassicalNode(consequent, node, null));
            node.setChildren(nodes);
        }

        return nodes;
    }

    protected boolean isNegation(PropositionalClassicalNode propositionNode, PropositionalClassicalNode parentNode) {
        String proposition = propositionNode.getProposition();
        String parent = parentNode.getProposition();
        if (proposition.charAt(0) == PropositionalUtilities.NEGATION) {
            return proposition.substring(1).equals(parent);
        } else {
            return proposition.equals(parent.substring(1));
        }
    }

    protected void getLowestChildNodes(PropositionalClassicalNode node, List<PropositionalClassicalNode> childNodes) {
        if (CollectionUtils.isEmpty(node.getChildren())) {
            childNodes.add(node);
        } else {
            for (PropositionalClassicalNode child : node.getChildren()) {
                getLowestChildNodes(child, childNodes);
            }
        }
    }
}
