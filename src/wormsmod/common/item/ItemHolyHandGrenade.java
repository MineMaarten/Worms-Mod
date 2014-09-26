package wormsmod.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;
import wormsmod.common.entity.projectile.EntityHolyHandGrenade;

public class ItemHolyHandGrenade extends ItemWormChargable{

    @Override
    protected EntityItem getProjectile(World world, EntityLivingBase throwingEntity, float chargeLevel){
        return new EntityHolyHandGrenade(world, throwingEntity, chargeLevel);
    }

    @Override
    protected boolean isTimed(){
        return false;
    }
}
