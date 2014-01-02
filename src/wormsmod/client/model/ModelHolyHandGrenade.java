package wormsmod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHolyHandGrenade extends ModelBase implements IBaseModel{
    //fields
    ModelRenderer Base;
    ModelRenderer Bottom;
    ModelRenderer Top;
    ModelRenderer Back;
    ModelRenderer Front;
    ModelRenderer Left;
    ModelRenderer Right;
    ModelRenderer Cross1;
    ModelRenderer Cross2;
    ModelRenderer Cross3;

    public ModelHolyHandGrenade(){
        textureWidth = 64;
        textureHeight = 32;

        Base = new ModelRenderer(this, 0, 0);
        Base.addBox(0F, 0F, 0F, 4, 4, 4);
        Base.setRotationPoint(-2F, 19F, -2F);
        Base.setTextureSize(64, 32);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        Bottom = new ModelRenderer(this, 0, 8);
        Bottom.addBox(0F, 0F, 0F, 2, 1, 2);
        Bottom.setRotationPoint(-1F, 23F, -1F);
        Bottom.setTextureSize(64, 32);
        Bottom.mirror = true;
        setRotation(Bottom, 0F, 0F, 0F);
        Top = new ModelRenderer(this, 0, 14);
        Top.addBox(0F, 0F, 0F, 2, 1, 2);
        Top.setRotationPoint(-1F, 18F, -1F);
        Top.setTextureSize(64, 32);
        Top.mirror = true;
        setRotation(Top, 0F, 0F, 0F);
        Back = new ModelRenderer(this, 0, 17);
        Back.addBox(0F, 0F, 0F, 2, 2, 1);
        Back.setRotationPoint(-1F, 20F, 2F);
        Back.setTextureSize(64, 32);
        Back.mirror = true;
        setRotation(Back, 0F, 0F, 0F);
        Front = new ModelRenderer(this, 0, 11);
        Front.addBox(0F, 0F, 0F, 2, 2, 1);
        Front.setRotationPoint(-1F, 20F, -3F);
        Front.setTextureSize(64, 32);
        Front.mirror = true;
        setRotation(Front, 0F, 0F, 0F);
        Left = new ModelRenderer(this, 0, 20);
        Left.addBox(0F, 0F, 0F, 1, 2, 2);
        Left.setRotationPoint(2F, 20F, -1F);
        Left.setTextureSize(64, 32);
        Left.mirror = true;
        setRotation(Left, 0F, 0F, 0F);
        Right = new ModelRenderer(this, 0, 24);
        Right.addBox(0F, 0F, 0F, 1, 2, 2);
        Right.setRotationPoint(-3F, 20F, -1F);
        Right.setTextureSize(64, 32);
        Right.mirror = true;
        setRotation(Right, 0F, 0F, 0F);
        Cross1 = new ModelRenderer(this, 0, 28);
        Cross1.addBox(0F, 0F, 0F, 1, 3, 1);
        Cross1.setRotationPoint(-0.5F, 15F, -0.5F);
        Cross1.setTextureSize(64, 32);
        Cross1.mirror = true;
        setRotation(Cross1, 0F, 0F, 0F);
        Cross2 = new ModelRenderer(this, 0, 28);
        Cross2.addBox(0F, 0F, 0F, 1, 1, 1);
        Cross2.setRotationPoint(0.5F, 16F, -0.5F);
        Cross2.setTextureSize(64, 32);
        Cross2.mirror = true;
        setRotation(Cross2, 0F, 0F, 0F);
        Cross3 = new ModelRenderer(this, 0, 28);
        Cross3.addBox(0F, 0F, 0F, 1, 1, 1);
        Cross3.setRotationPoint(-1.5F, 16F, -0.5F);
        Cross3.setTextureSize(64, 32);
        Cross3.mirror = true;
        setRotation(Cross3, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Base.render(f5);
        Bottom.render(f5);
        Top.render(f5);
        Back.render(f5);
        Front.render(f5);
        Left.render(f5);
        Right.render(f5);
        Cross1.render(f5);
        Cross2.render(f5);
        Cross3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z){
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void renderModel(float size){
        Base.render(size);
        Bottom.render(size);
        Top.render(size);
        Back.render(size);
        Front.render(size);
        Left.render(size);
        Right.render(size);
        Cross1.render(size);
        Cross2.render(size);
        Cross3.render(size);
    }

}
