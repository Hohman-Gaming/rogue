package com.elliotthohman.rogue.map;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

public class MapRogue {

	protected Map<Integer, MapChunk> chunkId2Chunk = new HashMap<Integer, MapChunk>();
	protected ChunkGenerator chunkGenerator = new ChunkGenerator();
	public Vector2 pos = new Vector2(0, MapChunk.CHUNK_DY/2);
	protected InputHandler inputHandler = new InputHandler(this);
	public Dude dude = new Dude(0, 260);
	
	
	
	public MapRogue() {
		
		// init with first 20 chunks in either direction
		MapChunk lastChunk = null;
		for(int i=-10;i<=10;i++) {
			chunkId2Chunk.put(i, chunkGenerator.generate(lastChunk, null));
			lastChunk = chunkId2Chunk.get(i);
			lastChunk.id = i;
		}
	}

	public void update() {
		inputHandler.handleInput();
		
		dude.update();
		
		
	}
	
	
	public int getMapWidth() {
		return chunkId2Chunk.size() * MapChunk.CHUNK_DX;
	}
	
	public int getMapHeight() {
		return MapChunk.CHUNK_DY;
	}
	
	public MapChunk getChunkAtPosition(float x) {
		int chunk = (int)Math.floor((double)(x / MapChunk.CHUNK_DX));
		return chunkId2Chunk.get(chunk);
	}
	
	public MapChunk getChunk(int chunkId) {
		return chunkId2Chunk.get(chunkId);
	}

}
