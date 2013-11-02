package wormsmod.client.render.entity;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import wormsmod.common.entity.projectile.timed.EntityWormTimedProjectile;

public class RenderWormProjectileEntity extends RenderItem{

    @Override
    public void doRender(Entity entity, double x, double y, double z, float f1, float partialTicks){
        GL11.glPushMatrix();
        GL11.glTranslated(0, 0.15D, 0);
        super.doRender(entity, x, y, z, f1, partialTicks);
        if(entity instanceof EntityWormTimedProjectile) {
            EntityWormTimedProjectile timedProjectile = (EntityWormTimedProjectile)entity;
            if(timedProjectile.getTimerValue() < 100) {
                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glTranslated(x, y + 0.3D, z);
                int timer = timedProjectile.getTimerValue() / 20 + 1;
                GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(/*180.0F -*/renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(180, 0, 0, 1);
                float timerSize = 0.03F;
                GL11.glScaled(timerSize, timerSize, timerSize);
                GL11.glTranslated(0, 0, -2);
                GL11.glColor4d(1, 1, 1, 1);
                renderManager.getFontRenderer().drawString(timer + "", -3, -3, 0xFFFFFFFF);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
            }
        }
        GL11.glPopMatrix();
    }
}
