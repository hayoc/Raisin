package hayoc.raisin.predicate.classical.search;

import hayoc.raisin.common.search.Node;
import hayoc.raisin.common.rules.Rule;
import hayoc.raisin.common.search.TableauxSearchImpl;
import hayoc.raisin.predicate.classical.rules.PredicateClassicalRuleUtilities;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Hayo on 04/01/2017.
 */
public class PredicateClassicalTableauxSearch extends TableauxSearchImpl {

    private static final Logger LOG = Logger.getLogger(PredicateClassicalTableauxSearch.class);

    private PredicateClassicalRuleUtilities ruleUtilities;

    @Inject
    public PredicateClassicalTableauxSearch(PredicateClassicalRuleUtilities ruleUtilities) {
        this.ruleUtilities = ruleUtilities;
    }

    @Override
    public boolean start(String goal) {
        return false;
    }

    @Override
    public List<Rule> getApplicableRules(Node proposition) {
        return null;
    }
}
