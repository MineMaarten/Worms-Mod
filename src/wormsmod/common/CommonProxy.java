package wormsmod.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{

    public void registerRenders(){

    }

    public World getClientWorld(){
        return null;
    }

    public void registerHandlers(){}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){

        // if(ID == PneumaticCraft.GUI_ID_AIR_COMPRESSOR) return new ContainerAirCompressor(player.inventory, (TileEntityAirCompressor)world.getBlockTileEntity(x, y, z));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){

        //(ID == PneumaticCraft.GUI_ID_AIR_COMPRESSOR) return new GuiAirCompressor(player.inventory, (TileEntityAirCompressor)world.getBlockTileEntity(x, y, z));
        return null;
    }

}
