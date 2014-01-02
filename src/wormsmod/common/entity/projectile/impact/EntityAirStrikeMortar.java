package wormsmod.common.entity.projectile.impact;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import wormsmod.common.lib.WeaponCharacteristics;

public class EntityAirStrikeMortar extends EntityWormImpactProjectile{
    public EntityAirStrikeMortar(World world){
        super(world);
    }

    public EntityAirStrikeMortar(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    public EntityAirStrikeMortar(World world, EntityLivingBase entity, float speed){
        super(world, entity, speed);
    }

    @Override
    public void explode(){
        explode(WeaponCharacteristics.AIR_STRIKE_ENVIRONMENT_DAMAGE, WeaponCharacteristics.AIR_STRIKE_ENTITY_DAMAGE, WeaponCharacteristics.AIR_STRIKE_ENTITY_PROPEL);
    }
}
