package hayoc.raisin.propositional.modal.rules;

import hayoc.raisin.propositional.classical.rules.PropositionalClassicalRuleUtilities;
import hayoc.raisin.propositional.classical.search.PropositionalClassicalNode;
import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.Rule;
import hayoc.raisin.propositional.modal.search.PropositionalModalNode;
import hayoc.raisin.setup.GuiceJUnitRunner;
import hayoc.raisin.setup.TestModule;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by hayoc on 8/21/2016.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class PropositionalModalRuleUtilitiesTest {

    private PropositionalModalRuleUtilities ruleUtilities = new PropositionalModalRuleUtilities();

    @Test
    public void dummy() {
        assertTrue(true);
    }

}
