package wormsmod.client.render;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import wormsmod.common.item.ItemWormChargable;
import wormsmod.common.lib.Constants;
import wormsmod.common.lib.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ProjectileChargeRenderer implements ITickHandler{
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData){

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData){
        if(type.contains(TickType.RENDER)) {
            Minecraft minecraft = FMLClientHandler.instance().getClient();
            EntityPlayer player = minecraft.thePlayer;
            ItemStack heldStack = null;
            Float partialTicks = (Float)tickData[0];
            if(player != null) {
                heldStack = player.getCurrentEquippedItem();
                if(heldStack != null && minecraft.inGameHasFocus && Item.itemsList[heldStack.getItem().itemID] instanceof ItemWormChargable) {
                    ScaledResolution sr = new ScaledResolution(minecraft.gameSettings, minecraft.displayWidth, minecraft.displayHeight);
                    // GL11.glDisable(GL11.GL_CULL_FACE);
                    //   GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);

                    GL11.glTranslated(sr.getScaledWidth() - 60, sr.getScaledHeight() - 70, 0);
                    GL11.glScaled(0.3D, 0.3D, 1);

                    drawChargeMeter(0, 0, Math.min(1, player.getItemInUseDuration() / (float)Constants.MAX_CHARGE_TIME));

                    //   GL11.glEnable(GL11.GL_CULL_FACE);
                }
            }
        }
    }

    public void drawChargeMeter(int x, int y, float progress){
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(Textures.GUI_CHARGE);
        float zLevel = 0F;

        GL11.glColor4d(1, 1, 1, 1);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + 200, zLevel, 0, 1);
        tessellator.addVertexWithUV(x + 128, y + 200, zLevel, 1, 1);
        tessellator.addVertexWithUV(x + 128, y + 200 - progress * 200, zLevel, 1, 1 - progress);
        tessellator.addVertexWithUV(x + 0, y + 200 - progress * 200, zLevel, 0, 1 - progress);
        tessellator.draw();

        GL11.glColor4d(0.2D, 0.2D, 0.2D, 1D);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + 200 - progress * 200, zLevel, 0, 1 - progress);
        tessellator.addVertexWithUV(x + 128, y + 200 - progress * 200, zLevel, 1, 1 - progress);
        tessellator.addVertexWithUV(x + 128, y, zLevel, 1, 0);
        tessellator.addVertexWithUV(x + 0, y, zLevel, 0, 0);
        tessellator.draw();

    }

    @Override
    public EnumSet<TickType> ticks(){

        return EnumSet.of(TickType.RENDER);
    }

    @Override
    public String getLabel(){

        return "Worms Mod Projectile Charge Handler";
    }

}