package wormsmod.client;

import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import wormsmod.client.model.ModelBananaBomb;
import wormsmod.client.model.ModelGrenade;
import wormsmod.client.render.AirStrikeRenderer;
import wormsmod.client.render.ProjectileChargeRenderer;
import wormsmod.client.render.entity.RenderAirPlane;
import wormsmod.client.render.entity.RenderBlitzCow;
import wormsmod.client.render.entity.RenderWormProjectileEntity;
import wormsmod.client.render.entity.item.RenderItemBase;
import wormsmod.client.render.entity.item.RenderItemBovineBlitz;
import wormsmod.common.CommonProxy;
import wormsmod.common.entity.EntityAirPlane;
import wormsmod.common.entity.projectile.EntityWormProjectile;
import wormsmod.common.entity.projectile.impact.EntityBlitzCow;
import wormsmod.common.item.Items;
import wormsmod.common.lib.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy{

    @Override
    public void registerRenders(){
        //ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPressureTube.class, new RenderPressureTube());

        MinecraftForgeClient.registerItemRenderer(Items.grenade.itemID, new RenderItemBase(new ModelGrenade(), Textures.MODEL_GRENADE));
        MinecraftForgeClient.registerItemRenderer(Items.clusterBomb.itemID, new RenderItemBase(new ModelGrenade(), Textures.MODEL_CLUSTER_BOMB));
        MinecraftForgeClient.registerItemRenderer(Items.bananaBomb.itemID, new RenderItemBase(new ModelBananaBomb(), Textures.MODEL_BANANA_BOMB));
        MinecraftForgeClient.registerItemRenderer(Items.bovineBlitz.itemID, new RenderItemBovineBlitz());

        RenderingRegistry.registerEntityRenderingHandler(EntityWormProjectile.class, new RenderWormProjectileEntity());
        RenderingRegistry.registerEntityRenderingHandler(EntityAirPlane.class, new RenderAirPlane());
        RenderingRegistry.registerEntityRenderingHandler(EntityBlitzCow.class, new RenderBlitzCow());
        MinecraftForge.EVENT_BUS.register(new AirStrikeRenderer());

        super.registerRenders();
    }

    @Override
    public void registerHandlers(){
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
        TickRegistry.registerTickHandler(new ProjectileChargeRenderer(), Side.CLIENT);
    }

    @Override
    public World getClientWorld(){
        return FMLClientHandler.instance().getClient().theWorld;
    }
}
