package wormsmod.common.entity.projectile.impact;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import wormsmod.common.lib.WeaponCharacteristics;

public class EntityClusterBombCluster extends EntityWormImpactProjectile{
    public EntityClusterBombCluster(World world){
        super(world);
    }

    public EntityClusterBombCluster(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    public EntityClusterBombCluster(World world, EntityLivingBase entity, float speed){
        super(world, entity, speed);
    }

    @Override
    public void explode(){
        explode(WeaponCharacteristics.CLUSTER_BOMB_ENVIRONMENT_DAMAGE, WeaponCharacteristics.CLUSTER_BOMB_ENVIRONMENT_DAMAGE, WeaponCharacteristics.CLUSTER_BOMB_ENTITY_PROPEL);
    }
}
