package hayoc.raisin.propositional.modal.search;

import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRuleUtilities;
import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import hayoc.raisin.propositional.classical.search.PropositionalClassicalTableauxSearch;
import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.modal.rules.PropositionalModalRuleUtilities;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Hayo on 22/08/2016.
 */
public class PropositionalModalTableauxSearch {

    private static final Logger LOG = Logger.getLogger(PropositionalModalTableauxSearch.class);

    private PropositionalModalRuleUtilities ruleUtilities;

    @Inject
    public PropositionalModalTableauxSearch(PropositionalModalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    public boolean start(String goal) {
        PropositionalModalNode goalNode = new PropositionalModalNode(goal);

        Queue<Node> propositions = new LinkedList<>();
        propositions.add(goalNode);

        List<Rule> rules = new ArrayList<>();

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

    protected List<Rule> getApplicableRules(Node proposition) {
        List<Rule> rules = new ArrayList<>();

        for (Class<?> clazz : PropositionalModalRuleUtilities.PROPOSITIONAL_MODAL_RULES) {
            try {
                Constructor<?> constructor =
                        clazz.getConstructor(PropositionalModalRuleUtilities.class);
                Rule rule =
                        (Rule) constructor.newInstance(new PropositionalModalRuleUtilities());
                if (rule.applicable(proposition))
                    rules.add(rule);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.error("Could not instantiate " + clazz.getSimpleName() + " with error: " + e);
            }
        }

        return rules;
    }

    protected Collection<Node> applyRules(List<Rule> rules) {
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
