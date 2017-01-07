package hayoc.raisin.predicate.classical.search;

import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.search.TableauxSearchImpl;
import hayoc.raisin.predicate.classical.common.VariableConstantMap;
import hayoc.raisin.predicate.classical.rules.PredicateClassicalRuleUtilities;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Hayo on 04/01/2017.
 */
public class PredicateClassicalTableauxSearch extends TableauxSearchImpl {

    private static final Logger LOG = Logger.getLogger(PredicateClassicalTableauxSearch.class);

    private PredicateClassicalRuleUtilities ruleUtilities;
    private VariableConstantMap variableConstantMap;

    @Inject
    public PredicateClassicalTableauxSearch(PredicateClassicalRuleUtilities ruleUtilities, VariableConstantMap variableConstantMap) {
        this.ruleUtilities = ruleUtilities;
        this.variableConstantMap = variableConstantMap;
    }

    @Override
    public boolean start(String goal) {
        PredicateClassicalNode goalNode = new PredicateClassicalNode(goal);

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

    @Override
    public List<Rule> getApplicableRules(Node proposition) {
        List<Rule> rules = new ArrayList<>();

        for (Class<?> clazz : PredicateClassicalRuleUtilities.PREDICATE_CLASSICAL_RULES) {
            try {
                Constructor<?> constructor =
                        clazz.getConstructor(PredicateClassicalRuleUtilities.class, VariableConstantMap.class);
                Rule rule =
                        (Rule) constructor.newInstance(ruleUtilities, variableConstantMap);
                if (rule.applicable(proposition))
                    rules.add(rule);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.error("Could not instantiate " + clazz.getSimpleName() + " with error: " + e);
            }
        }

        return rules;
    }
}
