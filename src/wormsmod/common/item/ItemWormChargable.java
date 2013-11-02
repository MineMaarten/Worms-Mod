package wormsmod.common.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import wormsmod.common.lib.Constants;

public abstract class ItemWormChargable extends Item{

    public ItemWormChargable(int par1){
        super(par1);
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int itemInUseCount){
        if(!world.isRemote) {
            int chargeTime = getMaxItemUseDuration(stack) - itemInUseCount;
            EntityItem projectile = getProjectile(world, player, Math.min(1, (float)chargeTime / Constants.MAX_CHARGE_TIME));
            ItemStack shotStack = stack.copy();
            shotStack.stackSize = 1;
            projectile.setEntityItemStack(shotStack);

            world.spawnEntityInWorld(projectile);
        }
        stack.stackSize--;
        if(stack.stackSize <= 0) player.setCurrentItemOrArmor(0, null);
    }

    @Override
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
        return par1ItemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack){
        return 72000;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){

        if(isTimed() && player.isSneaking()) {
            int damage = stack.getItemDamage();
            if(damage < 4) {
                stack.setItemDamage(damage + 1);
            } else {
                stack.setItemDamage(0);
            }
            if(!world.isRemote) player.addChatMessage(EnumChatFormatting.YELLOW + "Fuse: " + (stack.getItemDamage() + 1) + " seconds.");
        } else {
            player.setItemInUse(stack, getMaxItemUseDuration(stack));
        }
        return stack;
    }

    protected abstract boolean isTimed();

    protected abstract EntityItem getProjectile(World world, EntityLivingBase throwingEntity, float chargeLevel);

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4){
        infoList.add("Shift right click to change the length of the fuse.");
    }
}
