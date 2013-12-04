package wormsmod.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import wormsmod.client.model.ModelAirPlane;
import wormsmod.common.Utils;
import wormsmod.common.entity.EntityAirPlane;
import wormsmod.common.lib.Textures;

public class RenderAirPlane extends Render{
    protected ModelAirPlane model;

    public RenderAirPlane(){
        model = new ModelAirPlane();
    }

    @Override
    public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1){
        bindEntityTexture(entity);
        GL11.glPushMatrix();
        GL11.glTranslated(d0, d1, d2);
        GL11.glRotated(180, 0, 1, 0);
        EntityAirPlane plane = (EntityAirPlane)entity;
        Utils.rotateMatrixByMetadata(plane.getPlaneDirection().ordinal());
        GL11.glScaled(-1, -1, 1);
        model.renderModel(1 / 16F, plane.oldRotorRotation + (plane.rotorRotation - plane.oldRotorRotation) * f1);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity){
        return Textures.MODEL_AIR_PLANE;
    }
}
