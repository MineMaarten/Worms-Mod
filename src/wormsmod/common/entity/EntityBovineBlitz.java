package wormsmod.common.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import wormsmod.common.entity.projectile.impact.EntityBlitzCow;

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
        EntityBlitzCow tnt = new EntityBlitzCow(worldObj);
        //EntityTNTPrimed tnt = new EntityTNTPrimed(worldObj);
        //tnt.fuse = 100;
        tnt.setPosition(posX, posY, posZ);
        worldObj.spawnEntityInWorld(tnt);
        return --payloadsLeft > 0;
    }
}
