package hayoc.raisin.propositional.modal.rules;

import com.google.inject.Inject;
import hayoc.raisin.common.rules.AbstractRuleUtilities;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.propositional.modal.ModalUtilities;
import hayoc.raisin.propositional.modal.search.PropositionalModalNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class PropositionalModalRuleUtilities extends AbstractRuleUtilities {
    
    private ModalUtilities modalUtilities;

    public static final Class[] PROPOSITIONAL_MODAL_RULES = {BiconditionalRule.class, ConjunctionRule.class, DisjunctionRule.class, DoubleNegationRule.class,
            ImplicationRule.class, NecessityRule.class, NegatedBiconditionalRule.class, NegatedConjunctionRule.class, NegatedDisjunctionRule.class, NegatedImplicationRule.class,
            NegatedNeccesityRule.class, NegatedPossibilityRule.class, PossibilityRule.class};

    @Inject
    public PropositionalModalRuleUtilities (ModalUtilities modalUtilities) {
        this.modalUtilities = modalUtilities;
    }

    public List<Node> createSingleChild(Node parent, String proposition) {
        List<Node> nodes = new ArrayList<>();
        List<Node> childNodes = new ArrayList<>();
        getLowestChildNodes(parent, childNodes);
        for (Node node : childNodes) {
            if (branchClosed(node))
                continue;
            nodes.clear();
            Node childNode = new PropositionalModalNode(proposition, node, null);
            nodes.add(childNode);
            node.setChildren(nodes);
        }
        return nodes;
    }

    public List<Node> createSameBranchChildren(Node parent, String antecedent, String consequent) {
        List<Node> nodes = new ArrayList<>();

        List<Node> childNodes = new ArrayList<>();
        getLowestChildNodes(parent, childNodes);
        for (Node node : childNodes) {
            if (branchClosed(node))
                continue;
            nodes.clear();
            List<Node> newChildren = new ArrayList<>();
            Node consequentNode = new PropositionalModalNode(consequent, null, null);
            newChildren.add(consequentNode);

            Node antecedentNode = new PropositionalModalNode(antecedent, node, newChildren);
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
            nodes.add(new PropositionalModalNode(antecedent, node, null));
            nodes.add(new PropositionalModalNode(consequent, node, null));
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
            nodes.add(new PropositionalModalNode(proposition, node, null));
            node.setChildren(nodes);
        }

        return nodes;
    }

    @Override
    public boolean isNegation(Node propositionNode, Node parentNode) {
        String proposition = propositionNode.getProposition();
        String parent = parentNode.getProposition();
        if (proposition.charAt(0) == AbstractRuleUtilities.NEGATION && modalUtilities.getWorld(propositionNode) == modalUtilities.getWorld(parentNode)) {
            return proposition.substring(1).equals(parent);
        } else {
            return proposition.equals(parent.substring(1));
        }
    }
}
