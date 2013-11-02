package wormsmod.common.entity.projectile.timed;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import wormsmod.common.entity.projectile.impact.EntityClusterBombCluster;
import wormsmod.common.lib.WeaponCharacteristics;

public class EntityClusterBomb extends EntityWormTimedProjectile{

    public EntityClusterBomb(World world){
        super(world);
    }

    public EntityClusterBomb(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    public EntityClusterBomb(World world, EntityLivingBase entity, float speed){
        super(world, entity, speed);
    }

    @Override
    public void explode(){
        explode(WeaponCharacteristics.CLUSTER_BOMB_ENVIRONMENT_DAMAGE, WeaponCharacteristics.CLUSTER_BOMB_ENVIRONMENT_DAMAGE, WeaponCharacteristics.CLUSTER_BOMB_ENTITY_PROPEL);
        if(!worldObj.isRemote) {
            for(int i = 0; i < WeaponCharacteristics.CLUSTER_BOMB_CLUSTERS; i++) {
                EntityClusterBombCluster entity = new EntityClusterBombCluster(worldObj, posX, posY, posZ);
                entity.motionX = (rand.nextDouble() - 0.5D) * WeaponCharacteristics.CLUSTER_BOMB_CLUSTER_SPREAD;
                entity.motionY = 1.0D + rand.nextDouble() * 0.2D;
                entity.motionZ = (rand.nextDouble() - 0.5D) * WeaponCharacteristics.CLUSTER_BOMB_CLUSTER_SPREAD;
                entity.setEntityItemStack(getEntityItem().copy());
                worldObj.spawnEntityInWorld(entity);
            }
        }
    }
}
