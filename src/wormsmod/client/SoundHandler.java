package wormsmod.client;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import wormsmod.common.lib.Sounds;

public class SoundHandler{

    @ForgeSubscribe
    public void onSound(SoundLoadEvent event){
        try {
            event.manager.soundPoolSounds.addSound(Sounds.HOLY_HAND_GRENADE + ".ogg");
        } catch(Exception e) {
            System.err.println("Failed to register one or more sounds.");
        }

    }
}
