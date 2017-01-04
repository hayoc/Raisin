package hayoc.raisin.predicate.classical.search;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.search.NodeImpl;

import java.util.List;


/**
 * Created by Hayo on 04/01/2017.
 */
public class PredicateClassicalNode extends NodeImpl {

    public PredicateClassicalNode() {
        super();
    }

    public PredicateClassicalNode(String proposition) {
        super(proposition);
    }

    public PredicateClassicalNode(String proposition, List<Node> children) {
        super(proposition, children);
    }

    public PredicateClassicalNode(String proposition, Node parent, List<Node> children) {
        super(proposition, parent, children);
    }
}
