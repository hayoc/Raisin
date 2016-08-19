package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.PropositionalUtilities;
import hayoc.raisin.search.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class NegatedImplicationRule implements PropositionalClassicalRule {

    private Node node;
    private int splitPosition;

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) != PropositionalUtilities.NEGATION)
            return false;

        int parentheses = 0;
        for (int i = 0; i < proposition.getProposition().length(); i++) {
            char c = proposition.getProposition().charAt(i);
            if (c == PropositionalUtilities.OPEN_PARENTHESIS)
                parentheses++;
            if (c == PropositionalUtilities.CLOSE_PARENTHESIS)
                parentheses--;

            if (parentheses == 1 && c == PropositionalUtilities.CONDITIONAL) {
                splitPosition = i;
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Node> apply() {
        String antecedent = node.getProposition().substring(2, splitPosition).trim();
        String consequent = PropositionalUtilities.NEGATION + node.getProposition().substring(splitPosition + 1, node.getProposition().length() - 1).trim();

        List<Node> children = new ArrayList<>();
        children.add(new Node(consequent, node, null));

        Node antecedentNode = new Node(antecedent, node, children);

        List<Node> nodes = new ArrayList<>();
        nodes.add(antecedentNode);

        return nodes;
    }
}
