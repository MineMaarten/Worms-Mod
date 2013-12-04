package wormsmod.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import wormsmod.common.entity.EntityAirPlane;
import wormsmod.common.entity.EntityBovineBlitz;

public class ItemBovineBlitz extends ItemWormAirStrike{

    public ItemBovineBlitz(int par1){
        super(par1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
        EntityBovineBlitz entity = getControlledPlane(world, stack);
        if(entity != null) {
            if(!world.isRemote && !entity.releasePayload()) {
                setControlledPlane(-1, stack);
                stack.stackSize--;
            }
            return stack;
        } else if(!player.capabilities.isCreativeMode && isTrackingNonExistantPlane(world, stack)) {
            stack.stackSize--;
            return stack;
        } else {
            return super.onItemRightClick(stack, world, player);
        }
    }

    @Override
    protected EntityAirPlane getPlaneEntity(World world, ItemStack stack){
        EntityBovineBlitz bovineBlitz = new EntityBovineBlitz(world);
        setControlledPlane(bovineBlitz.entityId, stack);
        return bovineBlitz;
    }

    private void setControlledPlane(int entityID, ItemStack stack){
        if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setInteger("planeControlled", entityID);
    }

    private EntityBovineBlitz getControlledPlane(World world, ItemStack stack){
        if(stack.hasTagCompound() && stack.getTagCompound().getInteger("planeControlled") >= 0) {
            Entity entity = world.getEntityByID(stack.getTagCompound().getInteger("planeControlled"));
            if(entity instanceof EntityBovineBlitz) {
                return (EntityBovineBlitz)entity;
            }
        }
        return null;
    }

    private boolean isTrackingNonExistantPlane(World world, ItemStack stack){
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("planeControlled") >= 0 && world.getEntityByID(stack.getTagCompound().getInteger("planeControlled")) == null;
    }

    @Override
    protected boolean useItem(){
        return false;
    }

}
