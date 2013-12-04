package wormsmod.client.render.entity;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import wormsmod.common.lib.Textures;

public class RenderBlitzCow extends Render{

    private final ModelCow model;

    public RenderBlitzCow(){
        model = new ModelCow();
        model.isChild = false;
    }

    @Override
    public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1){
        bindEntityTexture(entity);
        GL11.glPushMatrix();
        GL11.glTranslated(d0, d1, d2);
        GL11.glScaled(-1, -1, 1);
        float legAngle = (float)Math.sin((entity.ticksExisted + f1) / 20);
        model.render(entity, 0, legAngle * 1, 0, 0, 0, 1 / 16F);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity){
        return Textures.MODEL_COW;
    }

}
