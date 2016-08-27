package hayoc.raisin.propositional.modal.search;

import java.util.List;

/**
 * Created by Hayo on 24/08/2016.
 */
public class WorldNode {

    private List<WorldNode> relativeWorlds;
    private int world;

    public WorldNode() {

    }

    public WorldNode(int world) {
        this.world = world;
    }

    public WorldNode(int world, List<WorldNode> relativeWorlds) {
        this.world = world;
        this.relativeWorlds = relativeWorlds;
    }

    public List<WorldNode> getRelativeWorlds() {
        return relativeWorlds;
    }

    public void setRelativeWorlds(List<WorldNode> relativeWorlds) {
        this.relativeWorlds = relativeWorlds;
    }

    public int getWorld() {
        return world;
    }

    public void setWorld(int world) {
        this.world = world;
    }
}
