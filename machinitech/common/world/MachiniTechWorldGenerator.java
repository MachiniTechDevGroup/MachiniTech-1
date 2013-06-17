package machinitech.common.world;

import java.util.Random;

import machinitech.common.block.OreMachiniTech;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class MachiniTechWorldGenerator implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.dimensionId){
        case -1:
            generateNether(world, random, chunkX * 16, chunkZ * 16);
            break;
        case 0:
            generateSurface(world, random, chunkX * 16, chunkZ * 16);
            break;
        case 1:
            generateEnd(world, random, chunkX * 16, chunkZ * 16);
            break;
        }
	}

	private void generateEnd(World world, Random rand, int chunkX, int chunkZ) {}

	private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		for (int i = OreMachiniTech.Ore_ID; i < OreMachiniTech.NUM_ORES + OreMachiniTech.Ore_ID; i++) {
			for (int m = 0; m < 16; m++) {
				if (OreMachiniTech.getBasedIDMeta(i, m) == null) {
					break;
				}
				OreMachiniTech.Params p =  OreMachiniTech.getBasedIDMeta(i, m).getParams();
				for(int k = 0; k < p.getNum(); k++){
		        	int firstBlockXCoord = chunkX + rand.nextInt(16);
		        	int firstBlockYCoord = rand.nextInt(p.getHigh() - p.getLow()) + p.getLow();
		        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
		        	
		        	(new WorldGenMinable(i, m, rand.nextInt(p.getOresHigh() - p.getOresLow()) + p.getOresLow(), Block.stone.blockID)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		        }	
			}
		}
		
	}

	private void generateNether(World world, Random rand, int chunkX, int chunkZ) {}
}
