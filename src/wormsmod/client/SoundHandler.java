package wormsmod.client;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandler{

    @ForgeSubscribe
    public void onSound(SoundLoadEvent event){
        try {
            //event.manager.soundPoolSounds.addSound(Sounds.CANNON_SOUND + ".ogg");

        } catch(Exception e) {
            System.err.println("Failed to register one or more sounds.");
        }

    }
}
