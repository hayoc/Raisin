package hayoc.raisin.search;

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
    private TableauxSearch tableauxSearch;

    @Test
    public void testIsNegation() {
        Node childNode = new Node("~(A > C)");
        Node parentNode = new Node("(A > C)");
        assertTrue(tableauxSearch.isNegation(childNode, parentNode));

        parentNode.setProposition("~(A > C)");
        assertFalse(tableauxSearch.isNegation(childNode, parentNode));

        childNode.setProposition("(A > C)");
        assertTrue(tableauxSearch.isNegation(childNode, parentNode));
    }

    @Test
    public void testBranchClosed() {
        Node leaf = new Node("A");
        Node subNode = new Node("~(A & C)");
        Node root = new Node("~A");

        leaf.setParent(subNode);
        subNode.setParent(root);
        assertTrue(tableauxSearch.branchClosed(leaf));

        leaf.setProposition("~A");
        assertFalse(tableauxSearch.branchClosed(leaf));

        Node leaf2 = new Node("A");
        leaf2.setParent(subNode);
        assertTrue(tableauxSearch.branchClosed(leaf2));
    }

    @Test
    public void testAllBranchesClosed() {
        Node root = new Node();
        Node child = new Node();
        Node child2 = new Node();
        child2.setClosed(true);

        List<Node> bottomChildNodes = new ArrayList<>();
        Node bottomChild = new Node();
        bottomChild.setClosed(true);
        Node bottomChild2 = new Node();
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
