package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.modal.search.PropositionalModalNode;

import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class BiconditionalRule implements PropositionalModalRule {

    @Override
    public boolean applicable(PropositionalModalNode proposition) {
        return false;
    }

    @Override
    public List<PropositionalModalNode> apply() {
        return null;
    }
}
