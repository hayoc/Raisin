package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.search.Node;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class NegatedConjunctionRule implements PropositionalClassicalRule {
    @Override
    public boolean applicable(Node proposition) {
        return false;
    }

    @Override
    public List<Node> apply(Node proposition) {
        return null;
    }
}
