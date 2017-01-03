package hayoc.raisin.propositional.common.search;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.Rule;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
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
