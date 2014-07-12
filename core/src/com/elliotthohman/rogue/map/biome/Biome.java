package com.elliotthohman.rogue.map.biome;

import java.util.Random;

import com.elliotthohman.rogue.Constants;
import com.elliotthohman.rogue.map.MapChunk;
import com.elliotthohman.rogue.map.MapRogue;
import com.elliotthohman.rogue.map.TileCodes;

public abstract class Biome {

	public int id = 0;
	public String typeString = null;
	public int maxBiome_dx = 0;
	public int minBiome_dx = 0;
	public static Biome[] biomes = new Biome[] {
		new BiomePlain(Constants.BIOME_ID_PLAIN, Constants.BIOME_NAME_PLAIN, 3, 6),
		new BiomeForest(Constants.BIOME_ID_FOREST, Constants.BIOME_NAME_FOREST, 5, 10),
		new BiomeJungle(Constants.BIOME_ID_JUNGLE, Constants.BIOME_NAME_JUNGLE, 10, 20)
		};
	
	public Biome(int id, String typeString, int minBiome_dx, int maxBiome_dx) {
		this.id = id;
		this.typeString = typeString;
		this.minBiome_dx = minBiome_dx;
		this.maxBiome_dx = maxBiome_dx;
	}
	
	public static Biome getRandomBiome(MapChunk left, Random random) {
		// return a valid next biome given what is to the left of us
		
		int biomePosition = Math.abs(random.nextInt()) % biomes.length;
		return biomes[biomePosition];
	}
	
	public static int getSizeOfBiomeToLeft(MapChunk me) {
		if (me == null)
			return 0;
		
		int size = 0;
		MapChunk chunk = me.left;
		while(chunk != null) {
			if (chunk.getBiomeId() != me.getBiomeId())
				break;
			size++;
			chunk = chunk.left;
		}
		return size;
		
	}
	
	// default biome generator, sub-biomes override this....
	public MapChunk generateChunk(int chunkId, MapRogue map, MapChunk left, Random random) {
		MapChunk chunk = new MapChunk(chunkId, this.id);

		chunk.left = left;
		
		// SUPER simple right now... top is air, then grass, then dirt
		// 0, 0 is lower left
		
		for(int xx=0;xx<MapChunk.CHUNK_DX;xx++) {
			int dirtHeight=(int)(MapChunk.CHUNK_DY/2);
			int yy=0;
			for(;yy<dirtHeight;yy++)
				chunk.tiles[xx][yy] = TileCodes.TILE_DIRT;
			chunk.tiles[xx][yy++] = TileCodes.TILE_GRASS;
			for(;yy<MapChunk.CHUNK_DY;yy++)
				chunk.tiles[xx][yy] = TileCodes.TILE_AIR;
		}
		
		return chunk;
	}
		
}
