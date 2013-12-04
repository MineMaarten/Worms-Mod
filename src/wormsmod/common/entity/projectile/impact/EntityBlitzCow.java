package wormsmod.common.entity.projectile.impact;

import net.minecraft.world.World;
import wormsmod.common.lib.WeaponCharacteristics;

public class EntityBlitzCow extends EntityWormImpactProjectile{

    public EntityBlitzCow(World world){
        super(world);
    }

    @Override
    public void explode(){
        explode(WeaponCharacteristics.BOVINE_BLITZ_ENVIRONMENT_DAMAGE, WeaponCharacteristics.BOVINE_BLITZ_ENTITY_DAMAGE, WeaponCharacteristics.BOVINE_BLITZ_ENTITY_PROPEL);
    }

}
