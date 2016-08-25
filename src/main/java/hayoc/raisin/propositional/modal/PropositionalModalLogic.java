package hayoc.raisin.propositional.modal;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hayoc.raisin.propositional.common.PropositionalSyntaxException;
import hayoc.raisin.propositional.modal.rules.PropositionalModalRuleUtilities;
import hayoc.raisin.propositional.modal.search.PropositionalModalTableauxSearch;
import hayoc.raisin.setup.RaisinModule;
import org.apache.log4j.Logger;

import javax.inject.Inject;

/**
 * Created by Hayo on 25/08/2016.
 */
public class PropositionalModalLogic {

    private static final Logger LOG = Logger.getLogger(PropositionalModalLogic.class);

    private PropositionalModalRuleUtilities propositionalUtilities;
    private PropositionalModalTableauxSearch tableauxSearch;

    @Inject
    public PropositionalModalLogic(PropositionalModalRuleUtilities propositionalUtilities, PropositionalModalTableauxSearch tableauxSearch) {
        this.propositionalUtilities = propositionalUtilities;
        this.tableauxSearch = tableauxSearch;
    }

    public boolean prove(String goal) {
        try {
            goal = propositionalUtilities.negate(goal);
        } catch (PropositionalSyntaxException e) {
            return false;
        }

        return tableauxSearch.start(goal);
    }

    public static void main(String[] args) {
        RaisinModule module = new RaisinModule();
        Injector injector = Guice.createInjector(module);
        if (injector.getInstance(PropositionalModalLogic.class).prove("")) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }
}
