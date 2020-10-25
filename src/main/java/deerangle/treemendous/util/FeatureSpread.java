package deerangle.treemendous.util;

import com.mojang.datafixers.Dynamic;

import java.util.Random;

public class FeatureSpread {

    private final int base;
    private final int variance;

    public FeatureSpread(int base, int variance) {
        this.base = base;
        this.variance = variance;
    }

    public static FeatureSpread func_242253_a(int base, int variance) {
        return new FeatureSpread(base, variance);
    }

    public static FeatureSpread func_242252_a(int base) {
        return func_242253_a(base, 0);
    }

    public static <T> FeatureSpread fromDynamic(Dynamic<T> dyn, String name) {
        return FeatureSpread.func_242253_a(dyn.get(name).asInt(0), dyn.get(name + "_random").asInt(0));
    }

    public int func_242259_a(Random random) {
        return base + random.nextInt(variance + 1);
    }

    public int getBase() {
        return this.base;
    }

    public int getVariance() {
        return this.variance;
    }

}
