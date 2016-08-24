package hayoc.raisin.propositional.classical.search;

import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRule;
import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRuleUtilities;
import hayoc.raisin.propositional.common.Node;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Hayo on 19/08/2016.
 */
public class PropositionalClassicalTableauxSearch {

    private static final Logger LOG = Logger.getLogger(PropositionalClassicalTableauxSearch.class);

    private PropositionalClassicalRuleUtilities ruleUtilities;

    @Inject
    public PropositionalClassicalTableauxSearch(PropositionalClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    public boolean start(String goal) {
        PropositionalClassicalNode goalNode = new PropositionalClassicalNode(goal);

        Queue<Node> propositions = new LinkedList<>();
        propositions.add(goalNode);

        List<PropositionalClassicalRule> rules = new ArrayList<>();

        while (!propositions.isEmpty()) {
            Node proposition = propositions.poll();
            if (!ruleUtilities.branchClosed(proposition)) {
                rules.addAll(getApplicableRules(proposition));
                propositions.addAll(applyRules(rules));
                rules.clear();
            }
        }

        return allBranchesClosed(goalNode);
    }

    protected List<PropositionalClassicalRule> getApplicableRules(Node proposition) {
        List<PropositionalClassicalRule> rules = new ArrayList<>();

        for (Class<?> clazz : PropositionalClassicalRuleUtilities.PROPOSITIONAL_CLASSICAL_RULES) {
            try {
                Constructor<?> constructor =
                        clazz.getConstructor(PropositionalClassicalRuleUtilities.class);
                PropositionalClassicalRule rule =
                         (PropositionalClassicalRule) constructor.newInstance(new PropositionalClassicalRuleUtilities());
                if (rule.applicable(proposition))
                    rules.add(rule);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.error("Could not instantiate " + clazz.getSimpleName() + " with error: " + e);
            }
        }

        return rules;
    }

    protected Collection<Node> applyRules(List<PropositionalClassicalRule> rules) {
        Collection<Node> results = new ArrayList<>();
        for (PropositionalClassicalRule rule : rules)
            results.addAll(rule.apply());

        Collection<Node> propositions = new ArrayList<>();
        for (Node result : results) {
            propositions.add(result);
            if (CollectionUtils.isNotEmpty(result.getChildren()))
                propositions.addAll(result.getChildren());
        }
        return propositions;
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
}
