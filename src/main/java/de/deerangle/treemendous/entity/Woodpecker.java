package de.deerangle.treemendous.entity;

import de.deerangle.treemendous.main.ExtraRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class Woodpecker extends Animal implements FlyingAnimal
{

    public float jumpProgression;

    public Woodpecker(Level level)
    {
        super(ExtraRegistry.WOODPECKER.get(), level);
    }

    public Woodpecker(EntityType<Woodpecker> entityType, Level level)
    {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.FLYING_SPEED, 0.4F)
                .add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 3.0f, 1.0f, 1.0f));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.level.isClientSide)
        {
            if (this.isPathFinding())
            {
                if (this.jumpProgression >= 1.0F)
                {
                    this.jumpProgression = 0.0F;
                }
                this.jumpProgression += this.getDeltaMovement().length() * 1.0F;
            } else
            {
                if (this.jumpProgression < 0.01f)
                {
                    this.jumpProgression = 0.0f;
                } else
                {
                    this.jumpProgression /= 2.0f;
                }
            }
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_)
    {
        return null;
    }

    @Override
    public boolean isFlying()
    {
        return false;// !this.onGround;
    }

}
