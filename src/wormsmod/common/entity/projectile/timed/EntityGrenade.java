package wormsmod.common.entity.projectile.timed;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityGrenade extends EntityWormTimedProjectile{

    public EntityGrenade(World world){
        super(world);
    }

    public EntityGrenade(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    public EntityGrenade(World world, EntityLivingBase entity, float speed){
        super(world, entity, speed);
    }

}
