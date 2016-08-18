package hayoc.raisin.propositional.classical;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRule;
import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRuleUtilities;
import hayoc.raisin.propositional.common.PropositionalSyntaxException;
import hayoc.raisin.propositional.common.PropositionalUtilities;
import hayoc.raisin.search.Node;
import hayoc.raisin.setup.RaisinModule;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Hayo on 16/08/2016.
 */
public class PropositionalClassicalLogic {

    private static final Logger LOG = Logger.getLogger(PropositionalClassicalLogic.class);

    private PropositionalUtilities propositionalUtilities;

    @Inject
    public PropositionalClassicalLogic(PropositionalUtilities propositionalUtilities) {
        this.propositionalUtilities = propositionalUtilities;
    }

    public boolean prove(String goal) {
        try {
            goal = propositionalUtilities.negate(goal);
        } catch (PropositionalSyntaxException e) {
            return false;
        }

        Queue<PropositionalClassicalRule> applicableRules = new LinkedList<PropositionalClassicalRule>();

        applicableRules.addAll(getApplicableRules(goal));

        return false;
    }

    private List<PropositionalClassicalRule> getApplicableRules(String proposition) {
        List<PropositionalClassicalRule> rules = new ArrayList<PropositionalClassicalRule>();

        Node node = new Node(proposition);

        for (Class clazz : PropositionalClassicalRuleUtilities.PROPOSITIONAL_CLASSICAL_RULES) {
            try {
                PropositionalClassicalRule rule = (PropositionalClassicalRule) clazz.newInstance();
                if (rule.applicable(node))
                    rules.add(rule);
            } catch (InstantiationException | IllegalAccessException e) {
                LOG.error("Could not instantiate " + clazz.getSimpleName() + " with error: " + e);
            }
        }

        return rules;
    }

    public static void main(String[] args) {
        RaisinModule module = new RaisinModule();
        Injector injector = Guice.createInjector(module);

        injector.getInstance(PropositionalClassicalLogic.class).prove("(((A > B) & (A > C)) > (A > (B & C)))");
    }
}
