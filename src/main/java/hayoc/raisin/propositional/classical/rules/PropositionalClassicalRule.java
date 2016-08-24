package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import hayoc.raisin.propositional.common.Node;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public interface PropositionalClassicalRule {

    boolean applicable(Node proposition);

    List<Node> apply();
}
