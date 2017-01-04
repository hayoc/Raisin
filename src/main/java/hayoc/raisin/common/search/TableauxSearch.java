package hayoc.raisin.common.search;

import hayoc.raisin.common.Node;
import hayoc.raisin.common.rules.Rule;

import java.util.Collection;
import java.util.List;

/**
 * Created by Hayo on 03/01/2017.
 */
public interface TableauxSearch {

    boolean start(String goal);

    List<Rule> getApplicableRules(Node proposition);

    Collection<Node> applyRules(List<Rule> rules);

    boolean allBranchesClosed(Node node);

    void getLeafNodes(Node node, List<Node> leafNodes);

}
