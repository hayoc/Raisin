package hayoc.raisin.propositional.classical;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hayoc.raisin.propositional.common.PropositionalSyntaxException;
import hayoc.raisin.propositional.common.PropositionalUtilities;
import hayoc.raisin.setup.RaisinModule;

import javax.inject.Inject;

/**
 * Created by Hayo on 16/08/2016.
 */
public class PropositionalClassicalLogic {

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
        return false;
    }

    public static void main(String[] args) {
        RaisinModule module = new RaisinModule();
        Injector injector = Guice.createInjector(module);

        injector.getInstance(PropositionalClassicalLogic.class).prove("(((A > B) & (A > C)) > (A > (B & C)))");
    }
}
