package hayoc.raisin.propositional.common;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import javax.inject.Singleton;

/**
 * Created by Hayo on 17/08/2016.
 */
@Singleton
public class PropositionalUtilities {

    private static final Logger LOG = Logger.getLogger(PropositionalUtilities.class);

    public static final char NEGATION = '~';
    public static final char INNER_PARENTHESIS = '(';
    public static final char OUTER_PARENTHESIS = ')';
    public static final char CONJUNCTION = '&';
    public static final char DISJUNCTION = '|';
    public static final char CONDITIONAL = '>';
    public static final char BICONDITIONAL = '=';
    public static final char[] BINARY_CONNECTIVES = {CONJUNCTION, DISJUNCTION, CONDITIONAL, BICONDITIONAL};

    public String negate(String goal) throws PropositionalSyntaxException {
        goal = NEGATION + validateParentheses(goal);
        LOG.info("Goal: " + goal);
        return goal;
    }

    private String validateParentheses(String goal) throws PropositionalSyntaxException {
        int inBrackets = 0;
        int parentheses = 0;
        boolean modify = false;
        for (int i = 0; i < goal.length(); i++) {
            char c = goal.charAt(i);

            if (c == INNER_PARENTHESIS) {
                inBrackets++;
                parentheses++;
            }
            if (c == OUTER_PARENTHESIS) {
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
        return modify ? INNER_PARENTHESIS + goal + OUTER_PARENTHESIS : goal;
    }
}
