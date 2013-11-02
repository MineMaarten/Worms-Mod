package wormsmod.common;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class Config{

    public static int grenadeID;
    public static int clusterBombID;
    public static int bananaBombID;

    public static void init(File configFile){
        Configuration config = new Configuration(configFile);
        config.load();

        // item ID's, 4096 - 31999
        grenadeID = config.getItem("Grenade ID", 12800).getInt();
        clusterBombID = config.getItem("Cluster Bomb ID", 12801).getInt();
        bananaBombID = config.getItem("Banana Bomb ID", 12802).getInt();

        config.save();
    }
}
