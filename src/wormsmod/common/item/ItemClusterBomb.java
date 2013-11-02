package wormsmod.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;
import wormsmod.common.entity.projectile.timed.EntityClusterBomb;

public class ItemClusterBomb extends ItemWormChargable{

    public ItemClusterBomb(int par1){
        super(par1);
    }

    @Override
    protected EntityItem getProjectile(World world, EntityLivingBase throwingEntity, float chargeLevel){
        return new EntityClusterBomb(world, throwingEntity, chargeLevel);
    }

    @Override
    protected boolean isTimed(){
        return true;
    }

}
