package wormsmod.common;

import net.minecraft.creativetab.CreativeTabs;
import wormsmod.client.CreativeTabWorms;
import wormsmod.common.entity.projectile.impact.EntityBananaBombCluster;
import wormsmod.common.entity.projectile.impact.EntityClusterBombCluster;
import wormsmod.common.entity.projectile.timed.EntityBananaBomb;
import wormsmod.common.entity.projectile.timed.EntityClusterBomb;
import wormsmod.common.entity.projectile.timed.EntityGrenade;
import wormsmod.common.item.Items;
import wormsmod.common.network.PacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

// TODO increase version
@Mod(modid = "WormsMod", name = "Worms Mod", version = "0.1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"wormsmod"}, packetHandler = PacketHandler.class)
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
        Items.init(wormsTab);
    }

    @EventHandler
    public void load(FMLInitializationEvent event){
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);

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
        // Entity Eggs:
        //registerEntityEgg(EntityStraightArrow.class, 0xffffff, 0x000000);

        // worldgenerators
        GameRegistry.registerWorldGenerator(new WorldGenerator());
    }

    public void languageRegisters(){
        LanguageRegistry.instance().addStringLocalization("itemGroup.WormsMod", "Worms Mod");

    }
}