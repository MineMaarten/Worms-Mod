package wormsmod.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import wormsmod.common.Config;
import wormsmod.common.entity.EntityAirPlane;
import wormsmod.common.entity.EntityAirStrike;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items{
    public static Item grenade;
    public static Item clusterBomb;
    public static Item bananaBomb;
    public static Item bovineBlitz;
    public static Item airStrike;
    public static Item holyHandGrenade;

    public static void init(CreativeTabs wormsTab){
        grenade = new ItemGrenade(Config.grenadeID).setUnlocalizedName("Grenade").setCreativeTab(wormsTab);
        clusterBomb = new ItemClusterBomb(Config.clusterBombID).setUnlocalizedName("ClusterBomb").setCreativeTab(wormsTab);
        bananaBomb = new ItemBananaBomb(Config.bananaBombID).setUnlocalizedName("BananaBomb").setCreativeTab(wormsTab);
        bovineBlitz = new ItemBovineBlitz(Config.bovineBlitzID).setUnlocalizedName("BovineBlitz").setCreativeTab(wormsTab);
        airStrike = new ItemWormAirStrike(Config.airStrikeID){
            @Override
            protected EntityAirPlane getPlaneEntity(World world, ItemStack stack){
                return new EntityAirStrike(world);
            }
        }.setUnlocalizedName("AirStrike").setCreativeTab(wormsTab);
        holyHandGrenade = new ItemHolyHandGrenade(Config.holyHandGrenadeID).setUnlocalizedName("HolyHandGrenade").setCreativeTab(wormsTab);

        GameRegistry.registerItem(grenade, "Grenade");
        GameRegistry.registerItem(clusterBomb, "ClusterBomb");
        GameRegistry.registerItem(bananaBomb, "BananaBomb");
        GameRegistry.registerItem(bovineBlitz, "BovineBlitz");
        GameRegistry.registerItem(airStrike, "AirStrike");
        GameRegistry.registerItem(holyHandGrenade, "HolyHandGrenade");

        LanguageRegistry.addName(grenade, "Grenade");
        LanguageRegistry.addName(clusterBomb, "Cluster Bomb");
        LanguageRegistry.addName(bananaBomb, "Banana Bomb");
        LanguageRegistry.addName(bovineBlitz, "Bovine Blitz");
        LanguageRegistry.addName(airStrike, "Air Strike");
        LanguageRegistry.addName(holyHandGrenade, "Holy Hand Grenade");
    }
}
