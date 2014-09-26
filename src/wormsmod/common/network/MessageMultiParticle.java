package wormsmod.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;

public class MessageMultiParticle extends LocationDoublePacket<MessageMultiParticle>{
    private String particle;
    private int particleAmount;
    private double particleRadius;

    public MessageMultiParticle(){

    }

    public MessageMultiParticle(int particleAmount, double particleRadius, String particle, double x, double y, double z){
        super(x, y, z);
        this.particleAmount = particleAmount;
        this.particleRadius = particleRadius;
        this.particle = particle;
    }

    @Override
    public void toBytes(ByteBuf buf){
        super.toBytes(buf);
        ByteBufUtils.writeUTF8String(buf, particle);
        buf.writeInt(particleAmount);
        buf.writeDouble(particleRadius);
    }

    @Override
    public void fromBytes(ByteBuf buf){
        super.fromBytes(buf);
        particle = ByteBufUtils.readUTF8String(buf);
        particleAmount = buf.readInt();
        particleRadius = buf.readDouble();
    }

    @Override
    public void handleClientSide(MessageMultiParticle message, EntityPlayer player){
        for(int i = 0; i < message.particleAmount; i++) {
            double randX = message.x + (player.getRNG().nextDouble() - 0.5D) * message.particleRadius;
            double randY = message.y + (player.getRNG().nextDouble() - 0.5D) * message.particleRadius;
            double randZ = message.z + (player.getRNG().nextDouble() - 0.5D) * message.particleRadius;
            player.worldObj.spawnParticle(message.particle, randX, randY, randZ, 0, 0, 0);
        }
    }

    @Override
    public void handleServerSide(MessageMultiParticle message, EntityPlayer player){

    }

}
