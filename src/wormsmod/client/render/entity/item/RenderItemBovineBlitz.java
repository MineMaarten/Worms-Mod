package wormsmod.client.render.entity.item;

import net.minecraft.client.model.ModelCow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import wormsmod.common.lib.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderItemBovineBlitz implements IItemRenderer{
    private final ModelCow model;

    public RenderItemBovineBlitz(){
        model = new ModelCow();
        model.isChild = false;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type){

        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper){

        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data){

        switch(type){
            case ENTITY: {
                render(0.0F, 0.0F, 1.3F, 1.0F);
                return;
            }
            case EQUIPPED: {
                render(0.5F, -2.5F, 1.7F, 1.0F);
                return;
            }
            case EQUIPPED_FIRST_PERSON: {
                render(0.7F, -0.7F, 1.9F, 1.0F);
                return;
            }
            case INVENTORY: {
                render(0.0F, 0.0F, 0.9F, 3F);
                return;
            }
            default:
                return;
        }
    }

    private void render(float x, float y, float z, float scale){

        GL11.glPushMatrix();
        GL11.glRotatef(-90F, 1F, 0, 0);

        // GL11.glDisable(GL11.GL_LIGHTING);

        // Scale, Translate, Rotate
        scale *= 0.25D;
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(-90F, 1F, 0, 0);

        // Bind texture
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(Textures.MODEL_COW);

        // Render
        model.render(null, 0, 0, 0, 0, 0, 1 / 16F);

        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
