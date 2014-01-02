package wormsmod.common.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import wormsmod.common.entity.projectile.impact.EntityBlitzCow;
import wormsmod.common.network.PacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;

public class EntityBovineBlitz extends EntityAirPlane{
    private int payloadsLeft = 3;

    public EntityBovineBlitz(World par1World){
        super(par1World);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag){
        super.readEntityFromNBT(tag);
        payloadsLeft = tag.getInteger("payloadsLeft");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag){
        super.writeEntityToNBT(tag);
        tag.setInteger("payloadsLeft", payloadsLeft);
    }

    public boolean releasePayload(){
        PacketDispatcher.sendPacketToAllAround(posX, posY, posZ, 64, worldObj.provider.dimensionId, PacketHandler.getMultiParticle(30, 2, "explode", posX, posY, posZ));
        EntityBlitzCow blitzCow = new EntityBlitzCow(worldObj);
        //EntityTNTPrimed tnt = new EntityTNTPrimed(worldObj);
        //tnt.fuse = 100;
        blitzCow.setPosition(posX, posY, posZ);
        worldObj.spawnEntityInWorld(blitzCow);
        return --payloadsLeft > 0;
    }
}
