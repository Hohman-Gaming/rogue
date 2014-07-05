package com.elliotthohman.rogue.map;


public class MapChunk {

	public static final int CHUNK_DX=32;
	public static final int CHUNK_DY=32*16;
	
	
	// note THAT THE tile[0][0] is in the lower left of the screen...  and then y increases as you go up the screen, x increases as you go right.
	public byte[][] tiles = new byte[CHUNK_DX][CHUNK_DY];
	public int id = 0;
	protected int biomeId = 0;
	
	public MapChunk(int biomeId) {
		this.biomeId = biomeId;
	}

	public int getBiomeId() {
		return biomeId;
	}
	
}
