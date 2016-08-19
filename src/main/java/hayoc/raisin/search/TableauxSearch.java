package hayoc.raisin.search;

import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRule;
import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRuleUtilities;
import hayoc.raisin.propositional.common.PropositionalUtilities;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by Hayo on 19/08/2016.
 */
public class TableauxSearch {

    private static final Logger LOG = Logger.getLogger(TableauxSearch.class);

    public boolean start(String goal) {
        Node goalNode = new Node(goal);

        Queue<Node> propositions = new LinkedList<>();
        propositions.add(goalNode);

        List<PropositionalClassicalRule> rules = new ArrayList<>();

        while (!propositions.isEmpty()) {
            Node proposition = propositions.poll();
            if (!branchClosed(proposition)) {
                rules.addAll(getApplicableRules(proposition));
                propositions.addAll(applyRules(rules));
            }
        }

        return allBranchesClosed(goalNode);
    }

    protected boolean allBranchesClosed(Node node) {
        List<Node> leafNodes = new ArrayList<>();
        getLeafNodes(node, leafNodes);
        for (Node leaf : leafNodes) {
            if (!leaf.isClosed())
                return false;
        }
        return true;
    }

    private void getLeafNodes(Node node, List<Node> leafNodes) {
        if (CollectionUtils.isEmpty(node.getChildren())) {
            leafNodes.add(node);
        } else {
            for (Node child : node.getChildren()) {
                getLeafNodes(child, leafNodes);
            }
        }
    }

    protected boolean branchClosed(Node proposition) {
        Node parent = proposition.getParent();
        while (parent != null) {
            if (isNegation(proposition, parent)) {
                proposition.setClosed(true);
                return true;
            }
            parent = parent.getParent();
        }
        proposition.setClosed(false);
        return false;
    }

    protected boolean isNegation(Node propositionNode, Node parentNode) {
        String proposition = propositionNode.getProposition();
        String parent = parentNode.getProposition();
        if (proposition.charAt(0) == PropositionalUtilities.NEGATION) {
            return proposition.substring(1).equals(parent);
        } else {
            return proposition.equals(parent.substring(1));
        }
    }

    protected List<PropositionalClassicalRule> getApplicableRules(Node proposition) {
        List<PropositionalClassicalRule> rules = new ArrayList<>();

        for (Class clazz : PropositionalClassicalRuleUtilities.PROPOSITIONAL_CLASSICAL_RULES) {
            try {
                PropositionalClassicalRule rule = (PropositionalClassicalRule) clazz.newInstance();
                if (rule.applicable(proposition))
                    rules.add(rule);
            } catch (InstantiationException | IllegalAccessException e) {
                LOG.error("Could not instantiate " + clazz.getSimpleName() + " with error: " + e);
            }
        }

        return rules;
    }

    protected Collection<Node> applyRules(List<PropositionalClassicalRule> rules) {
        Collection<Node> result = new ArrayList<>();
        for (PropositionalClassicalRule rule : rules)
            result.addAll(rule.apply());
        return result;
    }
}
