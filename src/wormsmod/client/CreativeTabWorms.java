package wormsmod.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import wormsmod.common.item.Itemss;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabWorms extends CreativeTabs{

    public CreativeTabWorms(String par2Str){

        super(par2Str);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem(){
        return Itemss.grenade;
    }

}
