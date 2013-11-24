package wormsmod.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import wormsmod.common.Config;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items{
    public static Item grenade;
    public static Item clusterBomb;
    public static Item bananaBomb;

    public static void init(CreativeTabs wormsTab){
        grenade = new ItemGrenade(Config.grenadeID).setUnlocalizedName("Grenade").setCreativeTab(wormsTab);
        clusterBomb = new ItemClusterBomb(Config.clusterBombID).setUnlocalizedName("ClusterBomb").setCreativeTab(wormsTab);
        bananaBomb = new ItemBananaBomb(Config.bananaBombID).setUnlocalizedName("BananaBomb").setCreativeTab(wormsTab);

        GameRegistry.registerItem(grenade, "Grenade");
        GameRegistry.registerItem(clusterBomb, "ClusterBomb");
        GameRegistry.registerItem(bananaBomb, "BananaBomb");

        LanguageRegistry.addName(grenade, "Grenade");
        LanguageRegistry.addName(clusterBomb, "Cluster Bomb");
        LanguageRegistry.addName(bananaBomb, "Banana Bomb");
    }

}
