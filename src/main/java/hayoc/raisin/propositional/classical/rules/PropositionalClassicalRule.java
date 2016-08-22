package hayoc.raisin.propositional.classical.rules;

import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public interface PropositionalClassicalRule {

    boolean applicable(PropositionalClassicalNode proposition);

    List<PropositionalClassicalNode> apply();
}
