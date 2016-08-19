package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.common.PropositionalUtilities;
import hayoc.raisin.search.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class ImplicationRule implements PropositionalClassicalRule {

    private Node node;
    private int splitPosition;

    @Override
    public boolean applicable(Node proposition) {
        this.node = proposition;

        if (proposition.getProposition().charAt(0) == PropositionalUtilities.NEGATION)
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
        String antecedent = PropositionalUtilities.NEGATION + node.getProposition().substring(1, splitPosition).trim();
        String consequent = node.getProposition().substring(splitPosition + 1, node.getProposition().length() - 1).trim();

        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(antecedent, node, null));
        nodes.add(new Node(consequent, node, null));

        return nodes;
    }
}
