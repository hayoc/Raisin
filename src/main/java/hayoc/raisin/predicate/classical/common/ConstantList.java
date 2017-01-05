package hayoc.raisin.predicate.classical.common;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hayo on 04/01/2017.
 */
public class ConstantList {

    private static final Logger LOG = Logger.getLogger(ConstantList.class);

    private List<String> constantList = new ArrayList<>();

    public String getUniqueConstant() {
        for (int i = 0; i < 100; i++) {
            if (!constantList.contains(String.valueOf(i))) {
                String constant = String.valueOf(i);
                constantList.add(constant);
                return constant;
            }
        }
        LOG.error("More than 100 constants have been assigned. This scenario is impossible - since there are only 26 letters in the alphabet to assign variables to.");
        throw new RuntimeException("The requested query has too many variables.");
    }

    public String getExistingOrNewConstant() {
        if (constantList.isEmpty()) {
            String constant = String.valueOf(0);
            constantList.add(constant);
            return constant;
        }
        return constantList.get(0);
    }

    public void clear() {
        constantList.clear();
    }
}
