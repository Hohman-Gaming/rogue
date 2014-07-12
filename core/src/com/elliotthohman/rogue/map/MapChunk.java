package com.elliotthohman.rogue.map;


public class MapChunk {

	public static final int CHUNK_DX=32;
	public static final int CHUNK_DY=32*16;
	
	
	// note THAT THE tile[0][0] is in the lower left of the screen...  and then y increases as you go up the screen, x increases as you go right.
	public byte[][] tiles = new byte[CHUNK_DX][CHUNK_DY];
	protected int id = 0;
	protected int biomeId = 0;
	public MapChunk left = null;
	public MapChunk right = null;
	
	public MapChunk(int chunkId, int biomeId) {
		this.id = chunkId;
		this.biomeId = biomeId;
	}

	public int getBiomeId() {
		return biomeId;
	}
	
	// return where the suface is for this x column
	public int getSurfaceY(int x) {
		int y = CHUNK_DY-1;
		for(y=CHUNK_DY-1;y>=0;y--) {
			byte tileCode = tiles[x][y];
			if (TileCodes.tileInfos[tileCode].solid)
				break;
		}
		return y;
	}
	
	public float getWorldCoordinateLeftSideOfChunk() {
		return this.id*MapChunk.CHUNK_DX;
	}
	
	public int getId() {
		return id;
	}
	
}
