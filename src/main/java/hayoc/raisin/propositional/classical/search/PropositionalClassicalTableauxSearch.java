package hayoc.raisin.propositional.classical.search;

import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRuleUtilities;
import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.common.search.TableauxSearchImpl;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Hayo on 19/08/2016.
 */
public class PropositionalClassicalTableauxSearch extends TableauxSearchImpl {

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

    public List<Rule> getApplicableRules(Node proposition) {
        List<Rule> rules = new ArrayList<>();

        for (Class<?> clazz : PropositionalClassicalRuleUtilities.PROPOSITIONAL_CLASSICAL_RULES) {
            try {
                Constructor<?> constructor =
                        clazz.getConstructor(PropositionalClassicalRuleUtilities.class);
                Rule rule =
                         (Rule) constructor.newInstance(ruleUtilities);
                if (rule.applicable(proposition))
                    rules.add(rule);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.error("Could not instantiate " + clazz.getSimpleName() + " with error: " + e);
            }
        }

        return rules;
    }
}
