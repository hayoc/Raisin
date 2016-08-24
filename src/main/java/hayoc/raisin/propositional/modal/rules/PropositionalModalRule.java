package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.modal.search.PropositionalModalNode;

import java.util.List;

/**
 * Created by Hayo on 22/08/2016.
 */
public interface PropositionalModalRule {

    boolean applicable(PropositionalModalNode proposition);

    List<PropositionalModalNode> apply();
}
