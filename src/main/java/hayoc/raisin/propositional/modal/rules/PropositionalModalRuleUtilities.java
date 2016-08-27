package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.common.rules.RuleUtilities;
import hayoc.raisin.propositional.modal.search.WorldNode;
import hayoc.raisin.propositional.common.Node;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class PropositionalModalRuleUtilities extends AbstractRuleUtilities {

    protected WorldNode getRelativeWorld(WorldNode world) {
        return null;
    }

    protected WorldNode setRelativeWorld(WorldNode world) {
        return null;
    }

    public int getConnectivePosition(Node proposition, char connective) {
        int parentheses = 0;
        for (int i = 0; i < proposition.getProposition().length(); i++) {
            char c = proposition.getProposition().charAt(i);
            if (c == AbstractRuleUtilities.OPEN_PARENTHESIS)
                parentheses++;
            if (c == AbstractRuleUtilities.CLOSE_PARENTHESIS)
                parentheses--;

            if (parentheses == 1 && c == connective)
                return i;
        }

        return 0;
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
