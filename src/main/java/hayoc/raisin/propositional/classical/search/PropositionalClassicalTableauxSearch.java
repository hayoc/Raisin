package hayoc.raisin.propositional.classical.search;

import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRule;
import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRuleUtilities;
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

        Queue<PropositionalClassicalNode> propositions = new LinkedList<>();
        propositions.add(goalNode);

        List<PropositionalClassicalRule> rules = new ArrayList<>();

        while (!propositions.isEmpty()) {
            PropositionalClassicalNode proposition = propositions.poll();
            if (!ruleUtilities.branchClosed(proposition)) {
                rules.addAll(getApplicableRules(proposition));
                propositions.addAll(applyRules(rules));
                rules.clear();
            }
        }

        return allBranchesClosed(goalNode);
    }

    protected List<PropositionalClassicalRule> getApplicableRules(PropositionalClassicalNode proposition) {
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

    protected Collection<PropositionalClassicalNode> applyRules(List<PropositionalClassicalRule> rules) {
        Collection<PropositionalClassicalNode> results = new ArrayList<>();
        for (PropositionalClassicalRule rule : rules)
            results.addAll(rule.apply());

        Collection<PropositionalClassicalNode> propositions = new ArrayList<>();
        for (PropositionalClassicalNode result : results) {
            propositions.add(result);
            if (CollectionUtils.isNotEmpty(result.getChildren()))
                propositions.addAll(result.getChildren());
        }
        return propositions;
    }

    protected boolean allBranchesClosed(PropositionalClassicalNode node) {
        List<PropositionalClassicalNode> leafNodes = new ArrayList<>();
        getLeafNodes(node, leafNodes);
        for (PropositionalClassicalNode leaf : leafNodes) {
            if (!leaf.isClosed())
                return false;
        }
        return true;
    }

    private void getLeafNodes(PropositionalClassicalNode node, List<PropositionalClassicalNode> leafNodes) {
        if (CollectionUtils.isEmpty(node.getChildren())) {
            leafNodes.add(node);
        } else {
            for (PropositionalClassicalNode child : node.getChildren()) {
                getLeafNodes(child, leafNodes);
            }
        }
    }
}