package deerangle.treemendous.entity;

import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CustomBoatType {
    private static final List<CustomBoatType> VALUES = new ArrayList<>();
    private final int id;
    private final String name;
    private final ResourceLocation texture;
    private final Supplier<IItemProvider> planksItem;
    private IItemProvider boatItem;

    private CustomBoatType(int id, String name, ResourceLocation texture, Supplier<IItemProvider> planksItem) {
        this.id = id;
        this.name = name;
        this.texture = texture;
        this.planksItem = planksItem;
    }

    public static CustomBoatType register(String name, ResourceLocation texture, Supplier<IItemProvider> planksItem) {
        CustomBoatType type = new CustomBoatType(VALUES.size(), name,
                new ResourceLocation(texture.getNamespace(), "textures/entity/boat/" + texture.getPath() + ".png"),
                planksItem);
        VALUES.add(type);
        return type;
    }

    public static CustomBoatType byId(int integer) {
        return VALUES.get(integer);
    }

    public static CustomBoatType getTypeFromString(String typeName) {
        for (CustomBoatType type : VALUES) {
            if (type.getName().equals(typeName)) {
                return type;
            }
        }
        return VALUES.get(0);
    }

    public int getId() {
        return id;
    }

    public IItemProvider getBoatItem() {
        return this.boatItem;
    }

    public void setBoatItem(IItemProvider boatItem) {
        this.boatItem = boatItem;
    }

    public String getName() {
        return this.name;
    }

    public IItemProvider getPlankBlock() {
        return this.planksItem.get();
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }
}
