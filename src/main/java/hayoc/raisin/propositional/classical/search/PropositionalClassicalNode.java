package hayoc.raisin.propositional.classical.search;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.search.NodeImpl;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class PropositionalClassicalNode extends NodeImpl {

    public PropositionalClassicalNode() {
        super();
    }

    public PropositionalClassicalNode(String proposition) {
        super(proposition);
    }

    public PropositionalClassicalNode(String proposition, List<Node> children) {
        super(proposition, children);
    }

    public PropositionalClassicalNode(String proposition, Node parent, List<Node> children) {
        super(proposition, parent, children);
    }
}
