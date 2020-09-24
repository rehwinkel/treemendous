package deerangle.treemendous.tree;

import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trees {

    private final List<Pair> trees;

    private Trees() {
        this.trees = new ArrayList<>();
    }

    public static Trees create() {
        return new Trees();
    }

    public Trees add(ConfiguredFeature<?, ?> feature) {
        this.trees.add(new Pair(1, feature));
        return this;
    }

    public Trees add(ConfiguredFeature<?, ?> feature, int part) {
        this.trees.add(new Pair(part, feature));
        return this;
    }

    public ConfiguredFeature<?, ?> getTree(Random random) {
        int totalParts = this.trees.stream().mapToInt(Pair::getPart).sum();
        int selected = random.nextInt(totalParts);
        int currentPart = 0;
        for (Pair p : this.trees) {
            if (selected >= currentPart && selected <= currentPart + p.getPart()) {
                return p.getTree();
            }
            currentPart += p.getPart();
        }
        return null;
    }

    private static class Pair {
        private final int part;
        private final ConfiguredFeature<?, ?> tree;

        public Pair(int part, ConfiguredFeature<?, ?> tree) {
            this.part = part;
            this.tree = tree;
        }

        public int getPart() {
            return part;
        }

        public ConfiguredFeature<?, ?> getTree() {
            return tree;
        }
    }

}
