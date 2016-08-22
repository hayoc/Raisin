package hayoc.raisin.propositional.classical.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class PropositionalClassicalNode {

    private String proposition;
    private PropositionalClassicalNode parent;
    private List<PropositionalClassicalNode> children;
    private boolean closed;
    private boolean branchChecked;

    public PropositionalClassicalNode() {}

    public PropositionalClassicalNode(String proposition) {
        this.proposition = proposition;
    }

    public PropositionalClassicalNode(String proposition, List<PropositionalClassicalNode> children) {
        this.proposition = proposition;
        this.children = children;
    }

    public PropositionalClassicalNode(String proposition, PropositionalClassicalNode parent, List<PropositionalClassicalNode> children) {
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

    public PropositionalClassicalNode getParent() {
        return parent;
    }

    public void setParent(PropositionalClassicalNode parent) {
        this.parent = parent;
    }

    public List<PropositionalClassicalNode> getChildren() {
        return children;
    }

    public void setChildren(List<PropositionalClassicalNode> children) {
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
