package wormsmod.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import wormsmod.common.Utils;
import wormsmod.common.item.ItemWormAirStrike;
import cpw.mods.fml.client.FMLClientHandler;

public class AirStrikeRenderer{
    @ForgeSubscribe
    public void render3D(RenderWorldLastEvent event){
        Minecraft mc = FMLClientHandler.instance().getClient();
        EntityPlayer player = mc.thePlayer;
        if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemWormAirStrike) {
            World world = mc.theWorld;
            //  System.out.println("posY: " + player.posY + ", eye: " + player.getEyeHeight() + ", offset: " + player.yOffset);
            Vec3 entityVec = world.getWorldVec3Pool().getVecFromPool(player.posX, player.posY, player.posZ);
            Vec3 entityLookVec = player.getLookVec();
            Vec3 maxDistVec = entityVec.addVector(entityLookVec.xCoord * ItemWormAirStrike.REACH, entityLookVec.yCoord * ItemWormAirStrike.REACH, entityLookVec.zCoord * ItemWormAirStrike.REACH);
            MovingObjectPosition hit = player.worldObj.clip(entityVec, maxDistVec);
            if(hit != null) {
                GL11.glPushMatrix();
                GL11.glTranslated(-player.posX, -player.posY, -player.posZ);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                Tessellator tess = Tessellator.instance;
                tess.startDrawing(GL11.GL_LINES);
                tess.addVertex(hit.blockX + 0.5, hit.blockY + 1, hit.blockZ + 0.5);
                tess.addVertex(hit.blockX + 0.5, hit.blockY + 10, hit.blockZ + 0.5);
                tess.draw();

                GL11.glEnable(GL11.GL_BLEND);
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glColor4d(0, 1, 0, 0.4D);
                tess.startDrawingQuads();
                double pathWidth = 2;
                double pathLength = 50;
                double pathHeight = hit.blockY + 20;
                GL11.glTranslated(hit.blockX + 0.5, 0, hit.blockZ + 0.5);
                Utils.rotateMatrixByMetadata(player.getCurrentEquippedItem().getItemDamage());
                tess.addVertex(pathWidth, pathHeight, -pathLength);
                tess.addVertex(-pathWidth, pathHeight, -pathLength);
                tess.addVertex(-pathWidth, pathHeight, pathLength);
                tess.addVertex(+pathWidth, pathHeight, pathLength);
                tess.draw();

                GL11.glTranslated(0, pathHeight, -pathLength - 2);
                for(int i = 0; i < pathLength; i++) {
                    GL11.glTranslated(0, 0, 2);
                    renderArrow(pathWidth);
                }

                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_CULL_FACE);
                GL11.glPopMatrix();
            }
        }
    }

    private void renderArrow(double pathWidth){
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.addVertex(-pathWidth, 0, 0);
        tess.addVertex(-pathWidth, 0, 0.5D);
        tess.addVertex(0, 0, 1.5);
        tess.addVertex(0, 0, 1D);

        tess.addVertex(pathWidth, 0, 0);
        tess.addVertex(pathWidth, 0, 0.5D);
        tess.addVertex(0, 0, 1.5);
        tess.addVertex(0, 0, 1);
        tess.draw();
    }
}
