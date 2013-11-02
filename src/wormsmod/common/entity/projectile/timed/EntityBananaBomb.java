package wormsmod.common.entity.projectile.timed;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import wormsmod.common.entity.projectile.impact.EntityBananaBombCluster;
import wormsmod.common.lib.WeaponCharacteristics;

public class EntityBananaBomb extends EntityWormTimedProjectile{

    public EntityBananaBomb(World world){
        super(world);
    }

    public EntityBananaBomb(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    public EntityBananaBomb(World world, EntityLivingBase entity, float speed){
        super(world, entity, speed);
    }

    @Override
    public void explode(){
        explode(WeaponCharacteristics.BANANA_BOMB_ENVIRONMENT_DAMAGE, WeaponCharacteristics.BANANA_BOMB_ENVIRONMENT_DAMAGE, WeaponCharacteristics.BANANA_BOMB_ENTITY_PROPEL);
        if(!worldObj.isRemote) {
            for(int i = 0; i < WeaponCharacteristics.BANANA_BOMB_CLUSTERS; i++) {
                EntityBananaBombCluster entity = new EntityBananaBombCluster(worldObj, posX, posY, posZ);
                entity.motionX = (rand.nextDouble() - 0.5D) * WeaponCharacteristics.BANANA_BOMB_CLUSTER_SPREAD;
                entity.motionY = 1.0D + rand.nextDouble() * 0.2D;
                entity.motionZ = (rand.nextDouble() - 0.5D) * WeaponCharacteristics.BANANA_BOMB_CLUSTER_SPREAD;
                entity.setEntityItemStack(getEntityItem().copy());
                worldObj.spawnEntityInWorld(entity);
            }
        }
    }
}
