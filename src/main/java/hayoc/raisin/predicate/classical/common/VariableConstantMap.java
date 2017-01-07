package hayoc.raisin.predicate.classical.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hayo on 04/01/2017.
 */
public class VariableConstantMap {

    private static final Logger LOG = Logger.getLogger(VariableConstantMap.class);

    private Map<String, String>  variableConstantMap = new HashMap<>();

    public String getUniqueConstant(String variable) {
        String constant = variableConstantMap.get(variable);
        if (StringUtils.isNotBlank(constant)) return constant;

        for (int i = 0; i < 100; i++) {
            if (!variableConstantMap.containsValue(String.valueOf(i))) {
                constant = String.valueOf(i);
                variableConstantMap.put(variable, constant);
                return constant;
            }
        }
        LOG.error("More than 100 constants have been assigned. This scenario is impossible - since there are only 26 letters in the alphabet to assign variables to.");
        throw new RuntimeException("The requested query has too many variables.");
    }

    public String getExistingOrNewConstant(String variable) {
        String constant = variableConstantMap.get(variable);
        if (StringUtils.isNotBlank(constant)) return constant;

        if (variableConstantMap.isEmpty()) {
            constant = String.valueOf(0);
            variableConstantMap.put(variable, constant);
            return constant;
        }
        return variableConstantMap.entrySet().iterator().next().getValue();
    }

    public void clear() {
        variableConstantMap.clear();
    }
}
