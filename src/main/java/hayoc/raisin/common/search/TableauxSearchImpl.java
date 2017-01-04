package hayoc.raisin.common.search;

import hayoc.raisin.common.rules.Rule;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Hayo on 03/01/2017.
 */
public abstract class TableauxSearchImpl implements TableauxSearch {

    public Collection<Node> applyRules(List<Rule> rules) {
        Collection<Node> results = new ArrayList<>();
        for (Rule rule : rules)
            results.addAll(rule.apply());

        Collection<Node> propositions = new ArrayList<>();
        for (Node result : results) {
            propositions.add(result);
            if (CollectionUtils.isNotEmpty(result.getChildren()))
                propositions.addAll(result.getChildren());
        }
        return propositions;
    }

    public boolean allBranchesClosed(Node node) {
        List<Node> leafNodes = new ArrayList<>();
        getLeafNodes(node, leafNodes);
        for (Node leaf : leafNodes) {
            if (!leaf.isClosed())
                return false;
        }
        return true;
    }

    public void getLeafNodes(Node node, List<Node> leafNodes) {
        if (CollectionUtils.isEmpty(node.getChildren())) {
            leafNodes.add(node);
        } else {
            for (Node child : node.getChildren()) {
                getLeafNodes(child, leafNodes);
            }
        }
    }
}
