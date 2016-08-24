package hayoc.raisin.propositional.classical.search;

import hayoc.raisin.propositional.common.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class PropositionalClassicalNode implements Node {

    private String proposition;
    private Node parent;
    private List<Node> children;
    private boolean closed;
    private boolean branchChecked;

    public PropositionalClassicalNode() {}

    public PropositionalClassicalNode(String proposition) {
        this.proposition = proposition;
    }

    public PropositionalClassicalNode(String proposition, List<Node> children) {
        this.proposition = proposition;
        this.children = children;
    }

    public PropositionalClassicalNode(String proposition, Node parent, List<Node> children) {
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
}
