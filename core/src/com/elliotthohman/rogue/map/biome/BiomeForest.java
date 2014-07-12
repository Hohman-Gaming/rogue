package com.elliotthohman.rogue.map.biome;

import java.util.Random;

import com.elliotthohman.rogue.map.MapChunk;
import com.elliotthohman.rogue.map.MapRogue;
import com.elliotthohman.rogue.map.entity.EntityTree;

public class BiomeForest extends Biome {

	protected static int NUM_TREES_PER_CHUNK = 10;
	protected static int MIN_TREE_SIZE = 5;
	protected static int MAX_TREE_SIZE = 20;
	
	public BiomeForest(int id, String typeString, int minBiome_dx,
			int maxBiome_dx) {
		super(id, typeString, minBiome_dx, maxBiome_dx);
	}

	@Override
	public MapChunk generateChunk(int chunkId, MapRogue map, MapChunk left, Random random) {
		MapChunk chunk = super.generateChunk(chunkId, map, left, random);
		
		// spawn some trees - between 0 and 10 trees
		int numTrees = random.nextInt() % NUM_TREES_PER_CHUNK;
		try {
			for(int i=0;i<numTrees;i++) {
				int x = Math.abs(random.nextInt()) % MapChunk.CHUNK_DX;
				
				int y = chunk.getSurfaceY(x);
	
				// TODO: see if there is already a tree there...
				
				// do we have room to put a tree here?
				if (y > MapChunk.CHUNK_DY-MIN_TREE_SIZE-2)
					continue;
				
				y++;
				
				// put a tree at x, y
				int height = MIN_TREE_SIZE + (Math.abs(random.nextInt())%(MAX_TREE_SIZE-MIN_TREE_SIZE));
				EntityTree tree = new EntityTree(map, chunk.getWorldCoordinateLeftSideOfChunk()+x, y, height);
				map.addEntity(tree);
			}
		} catch(Throwable t) {
			t.printStackTrace();
		}
		return chunk;
	}
	
}
