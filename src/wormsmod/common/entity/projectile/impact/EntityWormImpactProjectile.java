package wormsmod.common.entity.projectile.impact;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import wormsmod.common.entity.projectile.EntityWormProjectile;

public abstract class EntityWormImpactProjectile extends EntityWormProjectile{
    public EntityWormImpactProjectile(World world){
        super(world);
    }

    public EntityWormImpactProjectile(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    public EntityWormImpactProjectile(World world, EntityLivingBase entity, float speed){
        super(world, entity, speed);
    }

    @Override
    protected void onImpact(){
        explode();
    }

}
