package hayoc.raisin.predicate.classical;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hayoc.raisin.common.SyntaxException;
import hayoc.raisin.predicate.classical.rules.PredicateClassicalRuleUtilities;
import hayoc.raisin.predicate.classical.search.PredicateClassicalTableauxSearch;
import hayoc.raisin.setup.RaisinModule;
import org.apache.log4j.Logger;

import javax.inject.Inject;

/**
 * Created by Hayo on 13/09/2016.
 */
public class PredicateClassicalLogic {

    private static final Logger LOG = Logger.getLogger(PredicateClassicalLogic.class);

    private PredicateClassicalRuleUtilities predicateUtilities;
    private PredicateClassicalTableauxSearch tableauxSearch;

    @Inject
    public PredicateClassicalLogic(PredicateClassicalRuleUtilities predicateUtilities, PredicateClassicalTableauxSearch tableauxSearch) {
        this.predicateUtilities = predicateUtilities;
        this.tableauxSearch = tableauxSearch;
    }

    public boolean prove(String goal) {
        try {
            goal = predicateUtilities.negate(goal);
        } catch (SyntaxException e) {
            return false;
        }

        return tableauxSearch.start(goal);
    }

    public static void main(String[] args) {
        RaisinModule module = new RaisinModule();
        Injector injector = Guice.createInjector(module);

        //if (injector.getInstance(PredicateClassicalLogic.class).prove("(∀xAx > ∃xAx)")) {
        if (injector.getInstance(PredicateClassicalLogic.class).prove("((∀x(Px > Qx) & ∀x(Qx > Sx)) > ∀x(Px > Sx))")) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }
}
