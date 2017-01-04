package hayoc.raisin.predicate.classical.common;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hayo on 04/01/2017.
 */
public class VariableConstantMap {

    private static final Logger LOG = Logger.getLogger(VariableConstantMap.class);

    private Map<String, String> variableConstantMap = new HashMap<>();

    public String getUniqueConstant(String variable) {
        for (int i = 0; i < 100; i++) {
            if (!variableConstantMap.containsValue(String.valueOf(i))) {
                String constant = String.valueOf(i);
                variableConstantMap.put(variable, constant);
                return constant;
            }
        }
        LOG.error("More than 100 constants have been assigned. This scenario is impossible - since there are only 26 letters in the alphabet to assign variables to.");
        throw new RuntimeException("The requested query has too many variables.");
    }

    public String getExistingOrNewConstant(String variable) {
        if (variableConstantMap.isEmpty()) {
            String constant = String.valueOf(1);
            variableConstantMap.put(variable, constant);
            return constant;
        }
        return variableConstantMap.entrySet().iterator().next().getValue();
    }

    public String get(String variable) {
        return variableConstantMap.get(variable);
    }

    public void put(String variable, String constant) {
        variableConstantMap.put(variable, constant);
    }

    public void remove(String variable) {
        variableConstantMap.remove(variable);
    }

    public void clear() {
        variableConstantMap.clear();
    }

    public Map<String, String> getVariableConstantMap() {
        return variableConstantMap;
    }

    public void setVariableConstantMap(Map<String, String> variableConstantMap) {
        this.variableConstantMap = variableConstantMap;
    }
}
