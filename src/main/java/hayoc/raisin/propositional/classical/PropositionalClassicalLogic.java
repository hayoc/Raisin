package hayoc.raisin.propositional.classical;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRuleUtilities;
import hayoc.raisin.propositional.common.PropositionalSyntaxException;
import hayoc.raisin.propositional.classical.search.PropositionalClassicalTableauxSearch;
import hayoc.raisin.setup.RaisinModule;
import org.apache.log4j.Logger;

import javax.inject.Inject;

/**
 * Created by Hayo on 16/08/2016.
 */
public class PropositionalClassicalLogic {

    private static final Logger LOG = Logger.getLogger(PropositionalClassicalLogic.class);

    private PropositionalClassicalRuleUtilities propositionalUtilities;
    private PropositionalClassicalTableauxSearch tableauxSearch;

    @Inject
    public PropositionalClassicalLogic(PropositionalClassicalRuleUtilities propositionalUtilities, PropositionalClassicalTableauxSearch tableauxSearch) {
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
        //if (injector.getInstance(PropositionalClassicalLogic.class).prove("(((A > B) & (A > C)) > (A > (B & C)))")) {
        //(((p > q) | (r > q)) > ((p | r) > q))
        if (injector.getInstance(PropositionalClassicalLogic.class).prove("(((p > q) & (r > q)) > ((p | r) > q))")) {
            System.out.println("GOTTEM");
        } else {
            System.out.println("Dontgottem");
        }
    }
}
