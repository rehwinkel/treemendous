package de.deerangle.treemendous.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum LumberTiers implements Tier {
    IRON(2, 500, 6.0F, 2.0F, 14, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    }),
    DIAMOND(3, 3122, 8.0F, 3.0F, 10, () -> {
        return Ingredient.of(Items.DIAMOND);
    }),
    GOLD(0, 64, 12.0F, 0.0F, 22, () -> {
        return Ingredient.of(Items.GOLD_INGOT);
    }),
    NETHERITE(4, 4062, 9.0F, 4.0F, 15, () -> {
        return Ingredient.of(Items.NETHERITE_INGOT);
    });

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    LumberTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> ingredientSupplier) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = ingredientSupplier;
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @SuppressWarnings("NullableProblems")
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

}
