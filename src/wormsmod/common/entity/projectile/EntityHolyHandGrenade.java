package wormsmod.common.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import wormsmod.common.lib.Sounds;

public class EntityHolyHandGrenade extends EntityWormProjectile{
    private int soundToExplosionTimer;

    public EntityHolyHandGrenade(World world){
        super(world);
    }

    public EntityHolyHandGrenade(World world, EntityLivingBase throwingEntity, float chargeLevel){
        super(world, throwingEntity, chargeLevel);
    }

    @Override
    public void onUpdate(){
        super.onUpdate();
        if(!worldObj.isRemote && isLyingStill() && soundToExplosionTimer == 0) {
            soundToExplosionTimer = 40;
            // worldObj.playSoundAtEntity(this, Sounds.HOLY_HAND_GRENADE, 1, 1);
            worldObj.playSoundEffect(posX, posY, posZ, Sounds.HOLY_HAND_GRENADE, 1, 1);
        }
        if(soundToExplosionTimer > 0) {
            soundToExplosionTimer--;
            if(soundToExplosionTimer <= 0) {
                explode();
            }
        }
    }

    private boolean isLyingStill(){
        return Math.abs(motionX) < 0.01D && Math.abs(motionY) < 0.01D && Math.abs(motionZ) < 0.01D;
    }

}
