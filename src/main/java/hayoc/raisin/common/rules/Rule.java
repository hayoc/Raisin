package hayoc.raisin.common.rules;

import hayoc.raisin.common.Node;

import java.util.List;

/**
 * Created by Hayo on 25/08/2016.
 */
public interface Rule {

    boolean applicable(Node proposition);

    List<Node> apply();
}
