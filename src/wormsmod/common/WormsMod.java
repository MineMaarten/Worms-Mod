package wormsmod.common;

import net.minecraft.creativetab.CreativeTabs;
import wormsmod.client.CreativeTabWorms;
import wormsmod.common.entity.EntityAirPlane;
import wormsmod.common.entity.projectile.EntityHolyHandGrenade;
import wormsmod.common.entity.projectile.impact.EntityAirStrikeMortar;
import wormsmod.common.entity.projectile.impact.EntityBananaBombCluster;
import wormsmod.common.entity.projectile.impact.EntityBlitzCow;
import wormsmod.common.entity.projectile.impact.EntityClusterBombCluster;
import wormsmod.common.entity.projectile.timed.EntityBananaBomb;
import wormsmod.common.entity.projectile.timed.EntityClusterBomb;
import wormsmod.common.entity.projectile.timed.EntityGrenade;
import wormsmod.common.item.Itemss;
import wormsmod.common.network.NetworkHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

// TODO increase version
@Mod(modid = "WormsMod", name = "Worms Mod", version = "0.1.0")
public class WormsMod{

    @SidedProxy(clientSide = "wormsmod.client.ClientProxy", serverSide = "wormsmod.common.CommonProxy")
    public static CommonProxy proxy;

    @Instance("WormsMod")
    public static WormsMod instance = new WormsMod();

    @EventHandler
    public void PreInit(FMLPreInitializationEvent event){
        Config.init(event.getSuggestedConfigurationFile());
        proxy.registerHandlers();

        CreativeTabs wormsTab = new CreativeTabWorms("WormsMod");
        Itemss.init(wormsTab);
        NetworkHandler.init();
    }

    @EventHandler
    public void load(FMLInitializationEvent event){
        // NetworkRegistry.instance().registerGuiHandler(instance, proxy);

        gameRegisters();
        languageRegisters();

        proxy.registerRenders();
    }

    public void gameRegisters(){

        // new Tile Entities
        //GameRegistry.registerTileEntity(TileEntityPressureTube.class, "TileEntityPressureTube");

        // Crafting Recipes----------------------------------

        // Entities
        // parms: entity class, mobname (for spawners), id, modclass, max player
        // distance for update, update frequency, boolean keep server updated
        // about velocities.
        EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 0, this, 200, 1, true);
        EntityRegistry.registerModEntity(EntityClusterBomb.class, "ClusterBomb", 1, this, 200, 1, true);
        EntityRegistry.registerModEntity(EntityClusterBombCluster.class, "ClusterBombCluster", 2, this, 200, 1, true);
        EntityRegistry.registerModEntity(EntityBananaBomb.class, "BananaBomb", 3, this, 200, 1, true);
        EntityRegistry.registerModEntity(EntityBananaBombCluster.class, "BananaBombCluster", 4, this, 200, 1, true);
        EntityRegistry.registerModEntity(EntityAirPlane.class, "AirPlane", 5, this, 200, 1, true);
        EntityRegistry.registerModEntity(EntityBlitzCow.class, "BlitzCow", 6, this, 200, 1, true);
        EntityRegistry.registerModEntity(EntityAirStrikeMortar.class, "AirStrikeMortar", 7, this, 200, 1, true);
        EntityRegistry.registerModEntity(EntityHolyHandGrenade.class, "HolyHandGrenade", 8, this, 200, 1, true);
        // worldgenerators
        GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
    }

    public void languageRegisters(){
        LanguageRegistry.instance().addStringLocalization("itemGroup.WormsMod", "Worms Mod");

    }
}
