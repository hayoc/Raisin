package hayoc.raisin.propositional.modal;

import hayoc.raisin.propositional.common.Node;
import hayoc.raisin.propositional.common.rules.AbstractRuleUtilities;
import hayoc.raisin.propositional.modal.rules.PropositionalModalRuleUtilities;
import hayoc.raisin.propositional.modal.search.WorldNode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hayo on 27/08/2016.
 */
public class ModalUtilities {

    private static Map<Integer, List<Integer>> relativeWorlds = new HashMap<>();

    public static int getWorld(Node node) {
        String world = StringUtils.substringAfter(node.getProposition(), Character.toString(AbstractRuleUtilities.WORLD_SEPARATOR)).trim();
        return Integer.parseInt(world);
    }

    public static String getNonModalProposition(Node node) {
        return StringUtils.substringBefore(node.getProposition(), Character.toString(AbstractRuleUtilities.WORLD_SEPARATOR));
    }

    public static String writeWorld(int world) {
        return AbstractRuleUtilities.WORLD_SEPARATOR + StringUtils.SPACE + world;
    }

    public static void clearRelativeWorlds() {
        relativeWorlds.clear();
    }

    public static int getRelativeWorld(Node node) {
        List<Integer> worlds = relativeWorlds.get(getWorld(node));
        if (CollectionUtils.isEmpty(worlds))
            return -1;
        return worlds.get(0);
    }

    public static boolean isRelativeWorld(int worldA, int worldB) {
        for (int world : relativeWorlds.get(worldA)) {
            if (world == worldB)
                return true;
        }
        return false;
    }

    public static void addRelativeWorld(int worldA, int worldB) {
        List<Integer> worldsA = relativeWorlds.get(worldA) == null ? new ArrayList<>() : relativeWorlds.get(worldA);
        worldsA.add(worldB);

        List<Integer> worldsB = relativeWorlds.get(worldB) == null ? new ArrayList<>() : relativeWorlds.get(worldB);
        worldsB.add(worldA);

        relativeWorlds.put(worldA, worldsA);
        relativeWorlds.put(worldB, worldsB);
    }

    public static boolean isModal(Node node) {
        return node.getProposition().charAt(0) == AbstractRuleUtilities.POSSIBILITY || node.getProposition().charAt(0) == AbstractRuleUtilities.NECESSITY
                || node.getProposition().charAt(1) == AbstractRuleUtilities.POSSIBILITY || node.getProposition().charAt(1) == AbstractRuleUtilities.NECESSITY;
    }
}
