package de.deerangle.treemendous.entity;

import de.deerangle.treemendous.main.ExtraRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class CustomBoat extends Boat {

    private static final EntityDataAccessor<Integer> DATA_ID_BOAT_TYPE = SynchedEntityData.defineId(CustomBoat.class, EntityDataSerializers.INT);

    public CustomBoat(Level world, double x, double y, double z) {
        this(ExtraRegistry.BOAT.get(), world);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public CustomBoat(EntityType<CustomBoat> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_BOAT_TYPE, 0);
    }

    public BoatType getCustomBoatType() {
        return BoatType.byId(this.entityData.get(DATA_ID_BOAT_TYPE));
    }

    public void setCustomBoatType(BoatType woodName) {
        this.entityData.set(DATA_ID_BOAT_TYPE, woodName.getId());
    }

    @SuppressWarnings("NullableProblems")
    protected void addAdditionalSaveData(CompoundTag tag) {
        if (this.getCustomBoatType() != null) {
            tag.putInt("BoatType", this.getCustomBoatType().getId());
        }
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("BoatType", 3)) {
            this.setCustomBoatType(BoatType.byId(tag.getInt("BoatType")));
        }
    }

    @SuppressWarnings("NullableProblems")
    public Item getDropItem() {
        return this.getCustomBoatType().getBoatItem();
    }

}
