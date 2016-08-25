package hayoc.raisin.propositional.common.rules;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.PropositionalSyntaxException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Hayo on 17/08/2016.
 */
public abstract class AbstractRuleUtilities implements RuleUtilities {

    private static final Logger LOG = Logger.getLogger(AbstractRuleUtilities.class);

    public static final char NEGATION = '~';
    public static final char OPEN_PARENTHESIS = '(';
    public static final char CLOSE_PARENTHESIS = ')';
    public static final char CONJUNCTION = '&';
    public static final char DISJUNCTION = '|';
    public static final char CONDITIONAL = '>';
    public static final char BICONDITIONAL = '=';
    public static final char NECESSITY = '□';
    public static final char POSSIBILITY = '◊';
    public static final char[] BINARY_CONNECTIVES = {CONJUNCTION, DISJUNCTION, CONDITIONAL, BICONDITIONAL};

    public boolean branchClosed(Node proposition) {
        if (proposition.isBranchChecked())
            return proposition.isClosed();
        Node parent = proposition.getParent();
        while (parent != null) {
            if (isNegation(proposition, parent)) {
                proposition.setClosed(true);
                return true;
            }
            parent = parent.getParent();
        }
        proposition.setClosed(false);
        return false;
    }

    public void getLowestChildNodes(Node node, List<Node> childNodes) {
        if (CollectionUtils.isEmpty(node.getChildren())) {
            childNodes.add(node);
        } else {
            for (Node child : node.getChildren()) {
                getLowestChildNodes(child, childNodes);
            }
        }
    }

    public String negate(String goal) throws PropositionalSyntaxException {
        goal = NEGATION + validateParentheses(goal);
        LOG.info("Goal: " + goal);
        return goal;
    }

    public boolean isNegation(Node propositionNode, Node parentNode) {
        String proposition = propositionNode.getProposition();
        String parent = parentNode.getProposition();
        if (proposition.charAt(0) == AbstractRuleUtilities.NEGATION) {
            return proposition.substring(1).equals(parent);
        } else {
            return proposition.equals(parent.substring(1));
        }
    }

    public String validateParentheses(String goal) throws PropositionalSyntaxException {
        int inBrackets = 0;
        int parentheses = 0;
        boolean modify = false;
        for (int i = 0; i < goal.length(); i++) {
            char c = goal.charAt(i);

            if (c == OPEN_PARENTHESIS) {
                inBrackets++;
                parentheses++;
            }
            if (c == CLOSE_PARENTHESIS) {
                inBrackets--;
                parentheses++;
            }

            if (ArrayUtils.contains(BINARY_CONNECTIVES, c) && inBrackets == 0)
                modify = true;
        }
        if (parentheses%2 != 0) {
            LOG.error("Goal Syntax: Invalid amount of parentheses");
            throw new PropositionalSyntaxException();
        }
        return modify ? OPEN_PARENTHESIS + goal + CLOSE_PARENTHESIS : goal;
    }
}
