package wormsmod.client.render.entity.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import wormsmod.client.model.IBaseModel;
import wormsmod.client.model.ModelAirStrikeMortar;
import wormsmod.common.lib.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderItemAirstrike implements IItemRenderer{

    private final IBaseModel model;

    public RenderItemAirstrike(){
        model = new ModelAirStrikeMortar();
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
                render(0.5F, -0.8F, 1.7F, 1.0F);
                return;
            }
            case EQUIPPED_FIRST_PERSON: {
                render(0.7F, -0.7F, 1.9F, 1.0F);
                return;
            }
            case INVENTORY: {
                GL11.glPushMatrix();
                GL11.glRotatef(180F, 0, 1, 0);
                render(0.0F, 0.0F, 1.2F, 2F);
                GL11.glPopMatrix();
                return;
            }
            default:
                return;
        }
    }

    private void render(float x, float y, float z, float scale){

        GL11.glPushMatrix();
        GL11.glRotatef(-90F, 1F, 0, 0);

        // Scale, Translate, Rotate
        GL11.glScalef(scale, scale, scale);
        GL11.glRotated(45, 1, 0, 0);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(-90F, 1F, 0, 0);

        // Bind texture
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(Textures.MODEL_AIR_STRIKE_MORTAR);

        // Render
        model.renderModel(1F / 16F);

        GL11.glPopMatrix();
    }
}
