package wormsmod.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wormsmod.common.network.MessageMultiParticle;
import wormsmod.common.network.NetworkHandler;

public class EntityAirPlane extends Entity{
    protected static final double PLANE_SPEED = 0.4D;
    private static final int DW_DIRECTION_ID = 10;
    private static final int DROPZONE_DISTANCE = 40;
    private static final float ROTOR_SPEED = 0.5F;
    private static final float YAW_SPEED = 0.2F;
    private static final float PITCH_SPEED = 0.18F;
    private double dropX;
    private double dropY;
    private double dropZ;
    private boolean droppedPayload;
    public float rotorRotation;
    public float oldRotorRotation;
    public float yawProgress;
    public float pitchProgress;
    public float oldYawProgress;
    public float oldPitchProgress;

    public EntityAirPlane(World par1World){
        super(par1World);
        renderDistanceWeight = 10;
        setSize(3, 21 / 16F);
    }

    @Override
    protected void entityInit(){
        dataWatcher.addObject(DW_DIRECTION_ID, 2);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag){
        setPlaneDirection(ForgeDirection.getOrientation(tag.getInteger("planeDirection")));
        droppedPayload = tag.getBoolean("droppedPayload");
        dropX = tag.getDouble("dropX");
        dropY = tag.getDouble("dropY");
        dropZ = tag.getDouble("dropZ");

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag){
        tag.setInteger("planeDirection", getPlaneDirection().ordinal());
        tag.setBoolean("droppedPayload", droppedPayload);
        tag.setDouble("dropX", dropX);
        tag.setDouble("dropY", dropY);
        tag.setDouble("dropZ", dropZ);
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
        oldYawProgress = yawProgress;
        oldPitchProgress = pitchProgress;
        rotorRotation += ROTOR_SPEED;
        yawProgress += YAW_SPEED;
        pitchProgress += PITCH_SPEED;

        posX += getPlaneDirection().offsetX * PLANE_SPEED;
        posZ += getPlaneDirection().offsetZ * PLANE_SPEED;
        if(!worldObj.isRemote) {
            double dropzoneDistance = getDistanceFromDropzone();
            if(dropzoneDistance < -DROPZONE_DISTANCE) {
                setDead();
                NetworkHandler.sendToAllAround(new MessageMultiParticle(40, 2, "explode", posX, posY, posZ), worldObj);
            } else if(dropzoneDistance < 0 && !droppedPayload) {
                droppedPayload = true;
                dropPayload();
            }
        } else {
            if(ticksExisted == 1) {
                spawnMultiParticles(40, 2, "explode", posX, posY, posZ);
            }
            worldObj.spawnParticle("largesmoke", posX, posY + 1, posZ, 0, 0, 0);
        }
        super.onUpdate();
    }

    protected void dropPayload(){}

    public double getDistanceFromDropzone(){
        ForgeDirection dir = getPlaneDirection();
        return (dropX - posX) * dir.offsetX + (dropZ - posZ) * dir.offsetZ;
    }

    public void spawnMultiParticles(int particleAmount, double particleRadius, String particleName, double x, double y, double z){
        for(int i = 0; i < particleAmount; i++) {
            double randX = x + (rand.nextDouble() - 0.5D) * particleRadius;
            double randY = y + (rand.nextDouble() - 0.5D) * particleRadius;
            double randZ = z + (rand.nextDouble() - 0.5D) * particleRadius;
            worldObj.spawnParticle(particleName, randX, randY, randZ, 0, 0, 0);
        }
    }
}
