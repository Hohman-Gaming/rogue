package com.elliotthohman.rogue.map.biome;

import java.util.Random;

import com.elliotthohman.rogue.Constants;
import com.elliotthohman.rogue.map.MapChunk;
import com.elliotthohman.rogue.map.TileCodes;

public abstract class Biome {

	public int id = 0;
	public String typeString = null;
	public static Biome[] biomes = new Biome[] {
		new BiomeForest(Constants.BIOME_ID_FOREST, Constants.BIOME_NAME_FOREST),
		new BiomeJungle(Constants.BIOME_ID_JUNGLE, Constants.BIOME_NAME_JUNGLE)
		};
	
	public Biome(int id, String typeString) {
		this.id = id;
		this.typeString = typeString;
	}
	
	public static Biome getRandomBiome(Random random) {
		int biomePosition = Math.abs(random.nextInt()) % biomes.length;
		return biomes[biomePosition];
	}
	
	// default biome generator, sub-biomes override this....
	public MapChunk generateChunk(MapChunk left) {
		MapChunk chunk = new MapChunk(this.id);

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
