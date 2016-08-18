package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.search.Node;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public interface PropositionalClassicalRule {

    boolean applicable(Node proposition);

    List<Node> apply(Node proposition);
}
