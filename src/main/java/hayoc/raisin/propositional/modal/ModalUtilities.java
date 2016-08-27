package hayoc.raisin.propositional.modal;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.modal.rules.PropositionalModalRuleUtilities;
import hayoc.raisin.propositional.modal.search.WorldNode;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Hayo on 27/08/2016.
 */
public class ModalUtilities {

    public static WorldNode getWorld(Node node) {
        String world = StringUtils.substringAfter(node.getProposition(), Character.toString(AbstractRuleUtilities.WORLD_SEPARATOR)).trim();
        return new WorldNode(Integer.parseInt(world));
    }

    public static String getNonModalProposition(Node node) {
        return StringUtils.substringBefore(node.getProposition(), Character.toString(AbstractRuleUtilities.WORLD_SEPARATOR));
    }

    public static String addWorld(int world) {
        return AbstractRuleUtilities.WORLD_SEPARATOR + StringUtils.SPACE + world;
    }
}
