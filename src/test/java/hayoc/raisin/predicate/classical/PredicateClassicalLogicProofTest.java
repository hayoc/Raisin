package hayoc.raisin.predicate.classical;

import hayoc.raisin.setup.GuiceJUnitRunner;
import hayoc.raisin.setup.TestModule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hayo on 08/01/2017.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class PredicateClassicalLogicProofTest {

    private final static String GOAL1 = "((∀x(Px > Qx) & ∀x(Qx > Sx)) > ∀x(Px > Sx))";
    private final static String GOAL2 = "(∀x(Ax) > ∃x(Ax))";

    @Inject
    private PredicateClassicalLogic predicateClassicalLogic;

    @Test
    public void testPropositionalClassicalLogicProof() {
        assertTrue(predicateClassicalLogic.prove(GOAL1));
        assertTrue(predicateClassicalLogic.prove(GOAL2));
    }
}