package wormsmod.common;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator{

    public WorldGenerator(){}

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
        switch(world.provider.dimensionId){
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    public void generateSurface(World world, Random rand, int chunkX, int chunkZ){
        /*
         * //underground (hard) minefields if(configHardSR != 0 &&
         * rand.nextInt(configHardSR) == 0){ int baseX = chunkX +
         * rand.nextInt(8); int baseY = 7 + rand.nextInt(45); //TOD O make Y
         * level random again int baseZ = chunkZ + rand.nextInt(8); int maxX =
         * baseX + rand.nextInt(9) + 9; int maxZ = baseZ + rand.nextInt(9) + 9;
         * generateMinefield(world, rand, baseX, baseY, baseZ, maxX, maxZ, 2); }
         * 
         * //underground (medium) minefields if(configMediumSR != 0 &&
         * rand.nextInt(configMediumSR) == 0){ int baseX = chunkX +
         * rand.nextInt(8); int baseY = 7 + rand.nextInt(45);//T ODO make Y
         * level random again int baseZ = chunkZ + rand.nextInt(8); int maxX =
         * baseX + rand.nextInt(9) + 9; int maxZ = baseZ + rand.nextInt(9) + 9;
         * generateMinefield(world, rand, baseX, baseY, baseZ, maxX, maxZ, 1); }
         */
        /*
         * if(ChessMod.CONFIG_WORLDGEN_PUZZLE_UNDERGROUND != 0 &&
         * rand.nextInt(ChessMod.CONFIG_WORLDGEN_PUZZLE_UNDERGROUND) == 0 &&
         * !flatWorld(world, chunkX, chunkZ)){ for(int i = 0; i < 10; i++){ //10
         * generation attempts. int baseX = chunkX + rand.nextInt(8); int baseY
         * = rand.nextInt(30) + 20; int baseZ = chunkZ + rand.nextInt(8); int
         * maxX = baseX + rand.nextInt(9) + 9; int maxZ = baseZ +
         * rand.nextInt(9) + 9; if(collidingWithCave(world, baseX, baseY,
         * baseZ)){ generateChessPuzzle(world, rand, baseX, baseY, baseZ);
         * //System.out.println("generated at " + baseX + ", " + baseY + ", " +
         * baseZ); break; } }
         * 
         * }
         */

    }

    public void generateNether(World world, Random rand, int chunkX, int chunkZ){
        /*
         * //surface (easy) minefields if(configHardcoreSR != 0 &&
         * rand.nextInt(configHardcoreSR) == 0){ int baseX = chunkX +
         * rand.nextInt(8); int baseZ = chunkZ + rand.nextInt(8); int maxX =
         * baseX + rand.nextInt(9) + 9; int maxZ = baseZ + rand.nextInt(9) + 9;
         * int baseY = getFlatLandLevel(world, baseX, baseZ, maxX, maxZ);
         * if(baseY > 1) generateMinefield(world, rand, baseX, baseY, baseZ,
         * maxX, maxZ, 3); //generate hardcore fields in the Nether. }
         */
    }

    public void generateEnd(World world, Random rand, int chunkX, int chunkZ){

    }

}
