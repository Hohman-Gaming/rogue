package com.elliotthohman.rogue.map;


public class ChunkGenerator {

	public MapChunk generate(MapChunk left, MapChunk right) {
		MapChunk chunk = new MapChunk();

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
