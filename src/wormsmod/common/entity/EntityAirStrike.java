package wormsmod.common.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import wormsmod.common.entity.projectile.impact.EntityAirStrikeMortar;
import wormsmod.common.network.PacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;

public class EntityAirStrike extends EntityAirPlane{
    private int bombsLeft = 5;

    public EntityAirStrike(World par1World){
        super(par1World);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag){
        super.readEntityFromNBT(tag);
        bombsLeft = tag.getInteger("bombsLeft");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag){
        super.writeEntityToNBT(tag);
        tag.setInteger("bombsLeft", bombsLeft);
    }

    @Override
    public void onUpdate(){
        super.onUpdate();
        if(!worldObj.isRemote) {
            if(bombsLeft > 0 && getDistanceFromDropzone() < bombsLeft * 4 - 2) {
                bombsLeft--;
                EntityAirStrikeMortar airStrikeMortar = new EntityAirStrikeMortar(worldObj);
                airStrikeMortar.setPosition(posX, posY, posZ);
                airStrikeMortar.motionX = getPlaneDirection().offsetX * PLANE_SPEED;
                airStrikeMortar.motionZ = getPlaneDirection().offsetZ * PLANE_SPEED;
                worldObj.spawnEntityInWorld(airStrikeMortar);
                PacketDispatcher.sendPacketToAllAround(posX, posY, posZ, 64, worldObj.provider.dimensionId, PacketHandler.getMultiParticle(20, 1, "explode", posX, posY - 0.5D, posZ));
            }
        }
    }

}
