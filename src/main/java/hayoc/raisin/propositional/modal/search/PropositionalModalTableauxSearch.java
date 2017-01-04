package hayoc.raisin.propositional.modal.search;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.common.search.TableauxSearchImpl;
import hayoc.raisin.propositional.modal.ModalUtilities;
import hayoc.raisin.propositional.modal.rules.PropositionalModalRuleUtilities;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Hayo on 22/08/2016.
 */
public class PropositionalModalTableauxSearch extends TableauxSearchImpl {

    private static final Logger LOG = Logger.getLogger(PropositionalModalTableauxSearch.class);

    private PropositionalModalRuleUtilities ruleUtilities;
    private ModalUtilities modalUtilities;

    @Inject
    public PropositionalModalTableauxSearch(PropositionalModalRuleUtilities ruleUtilities, ModalUtilities modalUtilities) {
        this.ruleUtilities = ruleUtilities;
        this.modalUtilities = modalUtilities;
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

    public List<Rule> getApplicableRules(Node proposition) {
        List<Rule> rules = new ArrayList<>();

        for (Class<?> clazz : PropositionalModalRuleUtilities.PROPOSITIONAL_MODAL_RULES) {
            try {
                Constructor<?> constructor =
                        clazz.getConstructor(PropositionalModalRuleUtilities.class);
                Rule rule =
                        (Rule) constructor.newInstance(ruleUtilities, modalUtilities);
                if (rule.applicable(proposition))
                    rules.add(rule);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.error("Could not instantiate " + clazz.getSimpleName() + " with error: " + e);
            }
        }

        return rules;
    }
}
