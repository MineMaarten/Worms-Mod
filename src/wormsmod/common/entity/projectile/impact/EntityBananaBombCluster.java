package wormsmod.common.entity.projectile.impact;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import wormsmod.common.lib.WeaponCharacteristics;

public class EntityBananaBombCluster extends EntityWormImpactProjectile{
    public EntityBananaBombCluster(World world){
        super(world);
    }

    public EntityBananaBombCluster(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    public EntityBananaBombCluster(World world, EntityLivingBase entity, float speed){
        super(world, entity, speed);
    }

    @Override
    public void explode(){
        explode(WeaponCharacteristics.BANANA_BOMB_ENVIRONMENT_DAMAGE, WeaponCharacteristics.BANANA_BOMB_ENVIRONMENT_DAMAGE, WeaponCharacteristics.BANANA_BOMB_ENTITY_PROPEL);
    }
}
