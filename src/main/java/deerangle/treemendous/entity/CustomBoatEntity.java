package deerangle.treemendous.entity;

import deerangle.treemendous.main.ExtraRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class CustomBoatEntity extends BoatEntity {
    public CustomBoatEntity(EntityType<? extends CustomBoatEntity> type, World world) {
        super(type, world);
        this.preventEntitySpawning = true;
    }

    public CustomBoatEntity(World worldIn, double x, double y, double z) {
        this(ExtraRegistry.BOAT.get(), worldIn);
        this.setPosition(x, y, z);
        this.setMotion(Vector3d.ZERO);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    public CustomBoatEntity(World worldIn) {
        this(ExtraRegistry.BOAT.get(), worldIn);
        this.setMotion(Vector3d.ZERO);
    }

    public Item getItemBoat() {
        return this.getCustomBoatType().getBoatItem().asItem();
    }

    protected void writeAdditional(CompoundNBT compound) {
        compound.putString("Type", this.getCustomBoatType().getName());
    }

    protected void readAdditional(CompoundNBT compound) {
        if (compound.contains("Type", 8)) {
            this.setBoatType(CustomBoatType.getTypeFromString(compound.getString("Type")));
        }

    }

    public void setBoatType(CustomBoatType boatType) {
        this.dataManager.set(BOAT_TYPE, boatType.getId());
    }

    public CustomBoatType getCustomBoatType() {
        return CustomBoatType.byId(this.dataManager.get(BOAT_TYPE));
    }

    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
