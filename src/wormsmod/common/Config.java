package wormsmod.common;

import java.io.File;

import net.minecraftforge.common.Configuration;

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

        // item ID's, 4096 - 31999
        grenadeID = config.getItem("Grenade ID", 12800).getInt();
        clusterBombID = config.getItem("Cluster Bomb ID", 12801).getInt();
        bananaBombID = config.getItem("Banana Bomb ID", 12802).getInt();
        bovineBlitzID = config.getItem("Bovine Blitz ID", 12803).getInt();
        airStrikeID = config.getItem("Air Strike ID", 12804).getInt();
        holyHandGrenadeID = config.getItem("Holy Hand Grenade ID", 12805).getInt();

        config.save();
    }
}
