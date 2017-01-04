package hayoc.raisin.common.rules;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.propositional.common.PropositionalSyntaxException;

import java.util.List;

/**
 * Created by Hayo on 25/08/2016.
 */
public interface RuleUtilities {

    boolean branchClosed(Node proposition);

    void getLowestChildNodes(Node node, List<Node> childNodes);

    String negate(String goal) throws PropositionalSyntaxException;

    String validateParentheses(String goal) throws PropositionalSyntaxException;

    int getConnectivePosition(Node proposition, char connective);

    List<Node> createSameBranchChildren(Node parent, String antecedent, String consequent);

    List<Node> createSeparateBranchChildren(Node parent, String antecedent, String consequent);
}
