package hayoc.raisin.common.search;

import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayo on 04/01/2017.
 */
public abstract class NodeImpl implements Node {

    private String proposition;
    private Node parent;
    private List<Node> children;
    private boolean closed;
    private boolean branchChecked;

    public NodeImpl() {}

    public NodeImpl(String proposition) {
        this.proposition = proposition;
    }

    public NodeImpl(String proposition, List<Node> children) {
        this.proposition = proposition;
        this.children = children;
    }

    public NodeImpl(String proposition, Node parent, List<Node> children) {
        this.proposition = proposition;
        this.parent = parent;
        this.children = children;
    }

    public String getProposition() {
        return proposition;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void addChild(PropositionalClassicalNode child) {
        if (children == null)
            children = new ArrayList<>();
        children.add(child);
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
        this.branchChecked = true;
    }

    public boolean isBranchChecked() {
        return branchChecked;
    }

    public void setBranchChecked(boolean branchChecked) {
        this.branchChecked = branchChecked;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        sb.append(proposition);
        if (children != null && !children.isEmpty()) {
            sb.append(" ");
            for (Node child : children) {
                sb.append(child.toString());
            }
        }
        sb.append(" }");
        return sb.toString();
    }
}
