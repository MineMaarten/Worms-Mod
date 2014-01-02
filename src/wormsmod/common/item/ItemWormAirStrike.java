package wormsmod.common.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import wormsmod.common.entity.EntityAirPlane;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ItemWormAirStrike extends Item{
    public static final double REACH = 128;

    public ItemWormAirStrike(int par1){
        super(par1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List){
        par3List.add(new ItemStack(par1, 1, 2));
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
        if(!player.isSneaking()) {
            if(useItem() && !player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
            if(!world.isRemote) {
                Vec3 entityVec = world.getWorldVec3Pool().getVecFromPool(player.posX, player.posY + player.getEyeHeight() - player.yOffset, player.posZ);
                Vec3 entityLookVec = player.getLookVec();
                Vec3 maxDistVec = entityVec.addVector(entityLookVec.xCoord * REACH, entityLookVec.yCoord * REACH, entityLookVec.zCoord * REACH);
                MovingObjectPosition hit = player.worldObj.clip(entityVec, maxDistVec);
                if(hit != null) {
                    EntityAirPlane plane = getPlaneEntity(world, stack);
                    plane.setDropZone(hit.blockX + 0.5, hit.blockY, hit.blockZ + 0.5);
                    plane.setPlaneDirection(ForgeDirection.getOrientation(stack.getItemDamage()));
                    plane.setToStartPosition();
                    world.spawnEntityInWorld(plane);
                }
            }
        } else {
            stack.setItemDamage(stack.getItemDamage() + 1);
            if(stack.getItemDamage() > 5) {
                stack.setItemDamage(2);
            }
            player.addChatMessage("Set direction to " + ForgeDirection.getOrientation(stack.getItemDamage()));
        }
        return stack;
    }

    protected boolean useItem(){
        return true;
    }

    protected abstract EntityAirPlane getPlaneEntity(World world, ItemStack stack);

}
