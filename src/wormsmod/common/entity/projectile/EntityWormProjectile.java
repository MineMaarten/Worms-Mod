package wormsmod.common.entity.projectile;

import java.util.Iterator;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet60Explosion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import wormsmod.common.lib.WeaponCharacteristics;
import wormsmod.common.world.ExplosionWorms;

public abstract class EntityWormProjectile extends EntityItem{

    public EntityWormProjectile(World world){
        super(world);
        renderDistanceWeight = 10; //Will render the entity from really far :)
    }

    public EntityWormProjectile(World world, double x, double y, double z){
        super(world, x, y, z);
        renderDistanceWeight = 10;
    }

    public EntityWormProjectile(World world, EntityLivingBase entity, float speed){//copied from EntityThrowable
        super(world);
        setSize(0.25F, 0.25F);
        delayBeforeCanPickup = 20;
        setLocationAndAngles(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ, entity.rotationYaw, entity.rotationPitch);
        posX -= MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        float f = 0.4F;
        motionX = -MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI) * f;
        motionZ = MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI) * f;
        motionY = -MathHelper.sin((rotationPitch + func_70183_g()) / 180.0F * (float)Math.PI) * f;
        setThrowableHeading(motionX, motionY, motionZ, func_70182_d() * speed, 1.0F);
        renderDistanceWeight = 10;
    }

    protected float func_70182_d(){
        return 3F;
    }

    protected float func_70183_g(){
        return 0.0F;
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
     */
    public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8){
        float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= f2;
        par3 /= f2;
        par5 /= f2;
        par1 += rand.nextGaussian() * 0.007499999832361937D * par8;
        par3 += rand.nextGaussian() * 0.007499999832361937D * par8;
        par5 += rand.nextGaussian() * 0.007499999832361937D * par8;
        par1 *= par7;
        par3 *= par7;
        par5 *= par7;
        motionX = par1;
        motionY = par3;
        motionZ = par5;
        float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        prevRotationYaw = rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
        prevRotationPitch = rotationPitch = (float)(Math.atan2(par3, f3) * 180.0D / Math.PI);
    }

    @Override
    public void onUpdate(){
        age--; //make sure the item won't despawn.
        delayBeforeCanPickup = 20; //make sure the player never will be able to pick up an armed weapon.
        super.onUpdate();
        if(isCollided) onImpact();
    }

    protected void onImpact(){}

    /**
     * Base explosion
     */
    public void explode(){
        explode(WeaponCharacteristics.BASE_EXPLOSION_ENVIRONMENT_DAMAGE, WeaponCharacteristics.BASE_EXPLOSION_ENTITY_DAMAGE, WeaponCharacteristics.BASE_EXPLOSION_ENTITY_PROPEL);
    }

    public void explode(float baseDamage, double entityDamage, double entityPropel){
        if(!worldObj.isRemote) {

            ExplosionWorms explosion = new ExplosionWorms(worldObj, this, posX, posY, posZ, baseDamage, entityDamage, entityPropel);
            explosion.isFlaming = false;
            explosion.isSmoking = true;
            explosion.doExplosionA();
            explosion.doExplosionB(false);

            Iterator iterator = worldObj.playerEntities.iterator();

            //notify nearby clients
            while(iterator.hasNext()) {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();

                if(entityplayer.getDistanceSq(posX, posY, posZ) < 4096.0D) {
                    ((EntityPlayerMP)entityplayer).playerNetServerHandler.sendPacketToPlayer(new Packet60Explosion(posX, posY, posZ, baseDamage, explosion.affectedBlockPositions, (Vec3)explosion.func_77277_b().get(entityplayer)));
                }
            }

            setDead();
        }

    }

    @Override
    public boolean combineItems(EntityItem item){
        return false;
    }

}
