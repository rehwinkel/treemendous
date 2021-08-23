package de.deerangle.treemendous.tree;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BoatType {

    private static final Map<Integer, BoatType> boatTypes = new HashMap<>();

    static {
        boatTypes.put(0, new BoatType(0, "oak", () -> () -> Items.OAK_BOAT));
    }

    private final String name;
    private final Supplier<Supplier<Item>> boatItem;
    private final int id;

    private BoatType(int id, String name, Supplier<Supplier<Item>> boatItem) {
        this.id = id;
        this.name = name;
        this.boatItem = boatItem;
    }

    public static BoatType createAndRegister(String registryName, Supplier<Supplier<Item>> boatItem) {
        int id = boatTypes.size();
        BoatType type = new BoatType(id, registryName, boatItem);
        boatTypes.put(id, type);
        return type;
    }

    public static BoatType byId(int id) {
        return boatTypes.get(id);
    }

    public static Stream<BoatType> getBoatTypeStream() {
        return boatTypes.values().stream();
    }

    public Item getBoatItem() {
        return boatItem.get().get();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
