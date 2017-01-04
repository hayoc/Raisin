package hayoc.raisin.propositional.modal.search;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.search.NodeImpl;

import java.util.List;

/**
 * Created by Hayo on 22/08/2016.
 */
public class PropositionalModalNode extends NodeImpl {

    public PropositionalModalNode() {
        super();
    }

    public PropositionalModalNode(String proposition) {
        super(proposition);
    }

    public PropositionalModalNode(String proposition, List<Node> children) {
        super(proposition, children);
    }

    public PropositionalModalNode(String proposition, Node parent, List<Node> children) {
        super(proposition, parent, children);
    }
}
