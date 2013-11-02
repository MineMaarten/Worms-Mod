package wormsmod.common.entity.projectile.timed;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import wormsmod.common.entity.projectile.EntityWormProjectile;
import wormsmod.common.lib.WeaponCharacteristics;

public abstract class EntityWormTimedProjectile extends EntityWormProjectile{
    private float bouncyness = WeaponCharacteristics.DEFAULT_BOUNCYNESS;
    private double lastMotionX;
    private double lastMotionY;
    private double lastMotionZ;
    private static final int DATA_WATCHER_ID_TIMER = 2;

    public EntityWormTimedProjectile(World world){
        super(world);
    }

    public EntityWormTimedProjectile(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    public EntityWormTimedProjectile(World world, EntityLivingBase entity, float speed){
        super(world, entity, speed);
    }

    public EntityWormTimedProjectile(World world, EntityLivingBase entity, float speed, float bouncyness){
        this(world, entity, speed);
        this.bouncyness = bouncyness;
    }

    @Override
    protected void entityInit(){
        super.entityInit();
        dataWatcher.addObject(DATA_WATCHER_ID_TIMER, 0);
    }

    public void setTimerValue(int ticks){
        dataWatcher.updateObject(DATA_WATCHER_ID_TIMER, ticks);
    }

    public int getTimerValue(){
        return dataWatcher.getWatchableObjectInt(DATA_WATCHER_ID_TIMER);
    }

    @Override
    protected void onImpact(){
        if(Math.abs(motionX) < 0.1D) motionX = -lastMotionX * bouncyness;
        if(Math.abs(motionY) < 0.1D) motionY = -lastMotionY * bouncyness;
        if(Math.abs(motionZ) < 0.1D) motionZ = -lastMotionZ * bouncyness;
    }

    @Override
    public void onUpdate(){
        lastMotionX = motionX;
        lastMotionY = motionY;
        lastMotionZ = motionZ;
        super.onUpdate();
        if(!worldObj.isRemote) {
            if(getTimerValue() > 0) setTimerValue(getTimerValue() - 1);
            if(getTimerValue() == 0) explode();
        }
    }

    @Override
    public void setEntityItemStack(ItemStack stack){
        super.setEntityItemStack(stack);
        setTimerValue(stack.getItemDamage() * 20 + 20);
    }

}
