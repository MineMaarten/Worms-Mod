package wormsmod.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import wormsmod.client.model.ModelBananaBomb;
import wormsmod.client.model.ModelGrenade;
import wormsmod.client.model.ModelHolyHandGrenade;
import wormsmod.client.render.AirStrikeRenderer;
import wormsmod.client.render.ProjectileChargeRenderer;
import wormsmod.client.render.entity.RenderAirPlane;
import wormsmod.client.render.entity.RenderAirStrikeMortar;
import wormsmod.client.render.entity.RenderBlitzCow;
import wormsmod.client.render.entity.RenderWormProjectileEntity;
import wormsmod.client.render.entity.item.RenderItemAirstrike;
import wormsmod.client.render.entity.item.RenderItemBase;
import wormsmod.client.render.entity.item.RenderItemBovineBlitz;
import wormsmod.common.CommonProxy;
import wormsmod.common.entity.EntityAirPlane;
import wormsmod.common.entity.projectile.EntityWormProjectile;
import wormsmod.common.entity.projectile.impact.EntityAirStrikeMortar;
import wormsmod.common.entity.projectile.impact.EntityBlitzCow;
import wormsmod.common.item.Itemss;
import wormsmod.common.lib.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy{

    @Override
    public void registerRenders(){
        //ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPressureTube.class, new RenderPressureTube());

        MinecraftForgeClient.registerItemRenderer(Itemss.grenade, new RenderItemBase(new ModelGrenade(), Textures.MODEL_GRENADE));
        MinecraftForgeClient.registerItemRenderer(Itemss.clusterBomb, new RenderItemBase(new ModelGrenade(), Textures.MODEL_CLUSTER_BOMB));
        MinecraftForgeClient.registerItemRenderer(Itemss.bananaBomb, new RenderItemBase(new ModelBananaBomb(), Textures.MODEL_BANANA_BOMB));
        MinecraftForgeClient.registerItemRenderer(Itemss.bovineBlitz, new RenderItemBovineBlitz());
        MinecraftForgeClient.registerItemRenderer(Itemss.airStrike, new RenderItemAirstrike());
        MinecraftForgeClient.registerItemRenderer(Itemss.holyHandGrenade, new RenderItemBase(new ModelHolyHandGrenade(), Textures.MODEL_HOLY_HAND_GRENADE));

        RenderingRegistry.registerEntityRenderingHandler(EntityWormProjectile.class, new RenderWormProjectileEntity());
        RenderingRegistry.registerEntityRenderingHandler(EntityAirPlane.class, new RenderAirPlane());
        RenderingRegistry.registerEntityRenderingHandler(EntityBlitzCow.class, new RenderBlitzCow());
        RenderingRegistry.registerEntityRenderingHandler(EntityAirStrikeMortar.class, new RenderAirStrikeMortar());
        MinecraftForge.EVENT_BUS.register(new AirStrikeRenderer());

        super.registerRenders();
    }

    @Override
    public void registerHandlers(){
        FMLCommonHandler.instance().bus().register(new ProjectileChargeRenderer());
    }

    @Override
    public World getClientWorld(){
        return FMLClientHandler.instance().getClient().theWorld;
    }

    @Override
    public EntityPlayer getPlayer(){
        return FMLClientHandler.instance().getClient().thePlayer;
    }
}
