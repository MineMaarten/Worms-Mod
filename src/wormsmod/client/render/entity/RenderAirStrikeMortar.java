package wormsmod.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import wormsmod.client.model.ModelAirStrikeMortar;
import wormsmod.common.lib.Textures;

public class RenderAirStrikeMortar extends Render{
    protected ModelAirStrikeMortar model;

    public RenderAirStrikeMortar(){
        model = new ModelAirStrikeMortar();
    }

    @Override
    public void doRender(Entity entity, double d0, double d1, double d2, float f, float partialTicks){

        bindEntityTexture(entity);
        GL11.glPushMatrix();
        GL11.glTranslated(d0, d1, d2);

        GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks + 90, 0.0F, 0.0F, 1.0F);
        GL11.glScaled(-1, -1, 1);
        model.renderModel(1 / 16F);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity){
        return Textures.MODEL_AIR_STRIKE_MORTAR;
    }
}
