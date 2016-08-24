package hayoc.raisin.propositional.modal.search;

import hayoc.raisin.propositional.common.Node;

import java.util.List;

/**
 * Created by Hayo on 22/08/2016.
 */
public class PropositionalModalNode implements Node {

    private String proposition;
    private WorldNode world;

    private Node parent;
    private List<Node> children;

    private boolean closed;
    private boolean branchChecked;

    public PropositionalModalNode() {}

    public PropositionalModalNode(String proposition) {
        this.proposition = proposition;
    }

    public PropositionalModalNode(String proposition, List<Node> children) {
        this.proposition = proposition;
        this.children = children;
    }

    public PropositionalModalNode(String proposition, Node parent, List<Node> children) {
        this.proposition = proposition;
        this.parent = parent;
        this.children = children;
    }

    public PropositionalModalNode(String proposition, Node parent, List<Node> children, WorldNode world) {
        this.proposition = proposition;
        this.parent = parent;
        this.children = children;
        this.world = world;
    }

    public String getProposition() {
        return proposition;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }

    public WorldNode getWorld() {
        return world;
    }

    public void setWorld(WorldNode world) {
        this.world = world;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isBranchChecked() {
        return branchChecked;
    }

    public void setBranchChecked(boolean branchChecked) {
        this.branchChecked = branchChecked;
    }
}
