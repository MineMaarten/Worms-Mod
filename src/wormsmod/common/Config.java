package wormsmod.common;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config{

    public static int grenadeID;
    public static int clusterBombID;
    public static int bananaBombID;
    public static int bovineBlitzID;
    public static int airStrikeID;
    public static int holyHandGrenadeID;

    public static void init(File configFile){
        Configuration config = new Configuration(configFile);
        config.load();

        config.save();
    }
}
