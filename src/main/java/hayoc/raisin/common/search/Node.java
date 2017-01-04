package hayoc.raisin.common.search;

import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public interface Node {

    String getProposition();

    void setProposition(String proposition);

    Node getParent();

    void setParent(Node node);

    List<Node> getChildren();

    void setChildren(List<Node> children);

    boolean isClosed();

    void setClosed(boolean closed);

    boolean isBranchChecked();

    void setBranchChecked(boolean closed);

}
