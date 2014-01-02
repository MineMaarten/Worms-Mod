package wormsmod.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

    public static final int SPAWN_MULTIPLE_PARTICLE_ID = 0;

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player play){
        EntityPlayer player = (EntityPlayer)play;
        ByteArrayDataInput dat = ByteStreams.newDataInput(packet.data);
        try {
            int packetID = dat.readInt();
            double x, y, z;
            switch(packetID){
                case SPAWN_MULTIPLE_PARTICLE_ID:
                    int particleAmount = dat.readInt();
                    double particleRadius = dat.readDouble();
                    String particleName = dat.readUTF();
                    x = dat.readDouble();
                    y = dat.readDouble();
                    z = dat.readDouble();
                    for(int i = 0; i < particleAmount; i++) {
                        double randX = x + (player.getRNG().nextDouble() - 0.5D) * particleRadius;
                        double randY = y + (player.getRNG().nextDouble() - 0.5D) * particleRadius;
                        double randZ = z + (player.getRNG().nextDouble() - 0.5D) * particleRadius;
                        player.worldObj.spawnParticle(particleName, randX, randY, randZ, 0, 0, 0);
                    }
                    break;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Packet getMultiParticle(int particleAmount, double particleRadius, String particleName, double x, double y, double z){
        ByteArrayOutputStream bos = new ByteArrayOutputStream(140);
        DataOutputStream dos = new DataOutputStream(bos);
        try {
            dos.writeInt(SPAWN_MULTIPLE_PARTICLE_ID);
            dos.writeInt(particleAmount);
            dos.writeDouble(particleRadius);
            dos.writeUTF(particleName);
            dos.writeDouble(x);
            dos.writeDouble(y);
            dos.writeDouble(z);
        } catch(IOException e) {
            // System.out.println("catched the exception at PacketHandler.getPacket()");
            // UNPOSSIBLE?
        }
        Packet250CustomPayload pkt = new Packet250CustomPayload();
        pkt.channel = "wormsmod";
        pkt.data = bos.toByteArray();
        pkt.length = bos.size();
        pkt.isChunkDataPacket = true;
        return pkt;
    }

}
