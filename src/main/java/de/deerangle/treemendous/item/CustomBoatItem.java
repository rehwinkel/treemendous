package de.deerangle.treemendous.item;

import de.deerangle.treemendous.entity.BoatType;
import de.deerangle.treemendous.entity.CustomBoat;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class CustomBoatItem extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final BoatType boatType;

    public CustomBoatItem(BoatType boatType, Properties p_41383_) {
        super(p_41383_);
        this.boatType = boatType;
    }

    @SuppressWarnings("NullableProblems")
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        HitResult hitresult = getPlayerPOVHitResult(world, player, ClipContext.Fluid.ANY);
        if (hitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(heldItem);
        } else {
            Vec3 vec3 = player.getViewVector(1.0F);
            List<Entity> list = world.getEntities(player, player.getBoundingBox().expandTowards(vec3.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 vec31 = player.getEyePosition();

                for (Entity entity : list) {
                    AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (aabb.contains(vec31)) {
                        return InteractionResultHolder.pass(heldItem);
                    }
                }
            }

            if (hitresult.getType() == HitResult.Type.BLOCK) {
                CustomBoat boat = new CustomBoat(world, hitresult.getLocation().x, hitresult.getLocation().y, hitresult.getLocation().z);
                boat.setCustomBoatType(this.boatType);
                boat.setYRot(player.getYRot());
                if (!world.noCollision(boat, boat.getBoundingBox().inflate(-0.1D))) {
                    return InteractionResultHolder.fail(heldItem);
                } else {
                    if (!world.isClientSide) {
                        world.addFreshEntity(boat);
                        world.gameEvent(player, GameEvent.ENTITY_PLACE, new BlockPos(hitresult.getLocation()));
                        if (!player.getAbilities().instabuild) {
                            heldItem.shrink(1);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(heldItem, world.isClientSide());
                }
            } else {
                return InteractionResultHolder.pass(heldItem);
            }
        }
    }

}
