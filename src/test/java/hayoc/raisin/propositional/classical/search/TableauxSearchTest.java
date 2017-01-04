package hayoc.raisin.propositional.classical.search;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.setup.GuiceJUnitRunner;
import hayoc.raisin.setup.TestModule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Hayo on 19/08/2016.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class TableauxSearchTest {

    @Inject
    private PropositionalClassicalTableauxSearch tableauxSearch;

    @Test
    public void testAllBranchesClosed() {
        Node root = new PropositionalClassicalNode();
        Node child = new PropositionalClassicalNode();
        Node child2 = new PropositionalClassicalNode();
        child2.setClosed(true);

        List<Node> bottomChildNodes = new ArrayList<>();
        Node bottomChild = new PropositionalClassicalNode();
        bottomChild.setClosed(true);
        Node bottomChild2 = new PropositionalClassicalNode();
        bottomChild2.setClosed(true);
        bottomChildNodes.add(bottomChild);
        bottomChildNodes.add(bottomChild2);
        child.setChildren(bottomChildNodes);

        List<Node> childNodes = new ArrayList<>();
        childNodes.add(child);
        childNodes.add(child2);
        root.setChildren(childNodes);

        assertTrue(tableauxSearch.allBranchesClosed(root));
        child2.setClosed(false);
        assertFalse(tableauxSearch.allBranchesClosed(root));
    }
}
