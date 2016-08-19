package hayoc.raisin.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public class Node {

    private String proposition;
    private Node parent;
    private List<Node> children;
    private boolean closed;

    public Node() {}

    public Node(String proposition) {
        this.proposition = proposition;
    }

    public Node(String proposition, List<Node> children) {
        this.proposition = proposition;
        this.children = children;
    }

    public Node(String proposition, Node parent, List<Node> children) {
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

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void addChild(Node child) {
        if (children == null)
            children = new ArrayList<>();
        children.add(child);
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
