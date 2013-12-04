package wormsmod.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class EntityAirPlane extends Entity{
    private static final double PLANE_SPEED = 0.4D;
    private static final int DW_DIRECTION_ID = 10;
    private static final int DROPZONE_DISTANCE = 40;
    private static final float ROTOR_SPEED = 0.5F;
    private double dropX;
    private double dropY;
    private double dropZ;
    private boolean droppedPayload;
    public float rotorRotation;
    public float oldRotorRotation;

    public EntityAirPlane(World par1World){
        super(par1World);
        renderDistanceWeight = 10; //Will render the entity from really far :)
    }

    @Override
    protected void entityInit(){
        dataWatcher.addObject(DW_DIRECTION_ID, 2);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag){
        setPlaneDirection(ForgeDirection.getOrientation(tag.getInteger("planeDirection")));
        droppedPayload = tag.getBoolean("droppedPayload");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag){
        tag.setInteger("planeDirection", getPlaneDirection().ordinal());
        tag.setBoolean("droppedPayload", droppedPayload);
    }

    public void setPlaneDirection(ForgeDirection dir){
        dataWatcher.updateObject(DW_DIRECTION_ID, dir.ordinal());
    }

    public ForgeDirection getPlaneDirection(){
        return ForgeDirection.getOrientation(dataWatcher.getWatchableObjectInt(DW_DIRECTION_ID));
    }

    public void setDropZone(double x, double y, double z){
        dropX = x;
        dropY = y;
        dropZ = z;
    }

    public void setToStartPosition(){
        ForgeDirection dir = getPlaneDirection();
        setPosition(dropX - dir.offsetX * DROPZONE_DISTANCE, dropY + 20, dropZ - dir.offsetZ * DROPZONE_DISTANCE);
    }

    @Override
    public void onUpdate(){
        oldRotorRotation = rotorRotation;
        rotorRotation += ROTOR_SPEED;
        /*if(rotorRotation > 360) {
            rotorRotation -= 360;
            oldRotorRotation -= 360;
        }*/

        posX += getPlaneDirection().offsetX * PLANE_SPEED;
        posZ += getPlaneDirection().offsetZ * PLANE_SPEED;
        if(!worldObj.isRemote) {
            double dropzoneDistance = getDistanceFromDropzone();
            if(dropzoneDistance < -DROPZONE_DISTANCE) {
                setDead();
            } else if(dropzoneDistance < 0 && !droppedPayload) {
                droppedPayload = true;
                // EntityTNTPrimed tnt = new EntityTNTPrimed(worldObj, posX, posY, posZ, null);
                //tnt.fuse = 100;
                //worldObj.spawnEntityInWorld(tnt);
            }
        }
        super.onUpdate();
    }

    public double getDistanceFromDropzone(){
        ForgeDirection dir = getPlaneDirection();
        return (dropX - posX) * dir.offsetX + (dropZ - posZ) * dir.offsetZ;
    }

}
