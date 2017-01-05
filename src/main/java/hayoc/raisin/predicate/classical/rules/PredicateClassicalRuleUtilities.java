package hayoc.raisin.predicate.classical.rules;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.rules.AbstractRuleUtilities;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Hayo on 04/01/2017.
 */
public class PredicateClassicalRuleUtilities extends AbstractRuleUtilities {

    private static final Logger LOG = Logger.getLogger(PredicateClassicalRuleUtilities.class);

    @Override
    public List<Node> createSameBranchChildren(Node parent, String antecedent, String consequent) {
        return null;
    }

    @Override
    public List<Node> createSeparateBranchChildren(Node parent, String antecedent, String consequent) {
        return null;
    }
}
