package com.elliotthohman.rogue.map;

import java.util.HashMap;
import java.util.Map;

public class MapRogue {

	protected Map<Integer, MapChunk> chunkId2Chunk = new HashMap<Integer, MapChunk>();
	protected ChunkGenerator chunkGenerator = new ChunkGenerator();
	protected InputHandler inputHandler = new InputHandler(this);
	public Dude dude = new Dude(this, 0, 260);
	
	
	
	public MapRogue() {
		
		// init with first 20 chunks in either direction
		MapChunk lastChunk = null;
		for(int i=-10;i<=10;i++) {
			chunkId2Chunk.put(i, chunkGenerator.generate(lastChunk, null));
			lastChunk = chunkId2Chunk.get(i);
			lastChunk.id = i;
		}
	}

	public void update(float delta) {

		inputHandler.handleInput();

		dude.update(delta);
		
		
	}
	
	
	public int getMapWidth() {
		return chunkId2Chunk.size() * MapChunk.CHUNK_DX;
	}
	
	public int getMapHeight() {
		return MapChunk.CHUNK_DY;
	}
	
	public byte getTileAtPosition(float x, float y) {
		int chunkId = (int)Math.floor((double)(x / MapChunk.CHUNK_DX));
		MapChunk chunk = chunkId2Chunk.get(chunkId);
		int tx, ty;
		ty = (int)Math.floor(y);
		tx = (int)Math.floor(x - (float)chunkId*(float)MapChunk.CHUNK_DX); 
		return chunk.tiles[tx][ty];
	}
	
	public MapChunk getChunkAtPosition(float x) {
		int chunk = (int)Math.floor((double)(x / MapChunk.CHUNK_DX));
		return chunkId2Chunk.get(chunk);
	}
	
	public MapChunk getChunk(int chunkId) {
		return chunkId2Chunk.get(chunkId);
	}

}
