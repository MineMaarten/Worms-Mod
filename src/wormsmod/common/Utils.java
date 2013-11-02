package wormsmod.common;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.ForgeDirection;

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
}
