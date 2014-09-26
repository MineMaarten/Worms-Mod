package wormsmod.common;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Utils{
    public static ForgeDirection getDirectionFacing(EntityLivingBase entity){
        double yaw = entity.rotationYaw;
        while(yaw < 0)
            yaw += 360;
        yaw = yaw % 360;
        if(entity.rotationPitch > 45) return ForgeDirection.DOWN;
        else if(entity.rotationPitch < -45) return ForgeDirection.UP;
        else if(yaw < 45) return ForgeDirection.SOUTH;
        else if(yaw < 135) return ForgeDirection.WEST;
        else if(yaw < 225) return ForgeDirection.NORTH;
        else if(yaw < 315) return ForgeDirection.EAST;

        else return ForgeDirection.SOUTH;
    }

    @SideOnly(Side.CLIENT)
    public static void rotateMatrixByMetadata(int meta){
        switch(ForgeDirection.getOrientation(meta)){
            case EAST:
                GL11.glRotated(90, 0, 1, 0);
                break;
            case NORTH:
                GL11.glRotated(180, 0, 1, 0);
                break;
            case WEST:
                GL11.glRotated(-90, 0, 1, 0);
                break;
        }
    }
}
