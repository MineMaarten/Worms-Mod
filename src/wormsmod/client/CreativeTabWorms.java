package wormsmod.client;

import net.minecraft.creativetab.CreativeTabs;
import wormsmod.common.item.Items;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabWorms extends CreativeTabs{

    public CreativeTabWorms(String par2Str){

        super(par2Str);
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex(){
        return Items.grenade.itemID;
    }

}
