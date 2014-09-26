package wormsmod.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import wormsmod.common.entity.EntityAirPlane;
import wormsmod.common.entity.EntityAirStrike;
import cpw.mods.fml.common.registry.GameRegistry;

public class Itemss{
    public static Item grenade;
    public static Item clusterBomb;
    public static Item bananaBomb;
    public static Item bovineBlitz;
    public static Item airStrike;
    public static Item holyHandGrenade;

    public static void init(CreativeTabs wormsTab){
        grenade = new ItemGrenade().setUnlocalizedName("grenade").setCreativeTab(wormsTab);
        clusterBomb = new ItemClusterBomb().setUnlocalizedName("clusterBomb").setCreativeTab(wormsTab);
        bananaBomb = new ItemBananaBomb().setUnlocalizedName("bananaBomb").setCreativeTab(wormsTab);
        bovineBlitz = new ItemBovineBlitz().setUnlocalizedName("bovineBlitz").setCreativeTab(wormsTab);
        airStrike = new ItemWormAirStrike(){
            @Override
            protected EntityAirPlane getPlaneEntity(World world, ItemStack stack){
                return new EntityAirStrike(world);
            }
        }.setUnlocalizedName("AirStrike").setCreativeTab(wormsTab);
        holyHandGrenade = new ItemHolyHandGrenade().setUnlocalizedName("holyHandGrenade").setCreativeTab(wormsTab);

        GameRegistry.registerItem(grenade, "grenade");
        GameRegistry.registerItem(clusterBomb, "clusterBomb");
        GameRegistry.registerItem(bananaBomb, "bananaBomb");
        GameRegistry.registerItem(bovineBlitz, "bovineBlitz");
        GameRegistry.registerItem(airStrike, "airStrike");
        GameRegistry.registerItem(holyHandGrenade, "holyHandGrenade");

    }
}
