package com.elliotthohman.rogue.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.elliotthohman.rogue.map.biome.Biome;
import com.elliotthohman.rogue.map.entity.EntityBase;
import com.elliotthohman.rogue.map.entity.EntityDude;

public class MapRogue {

	protected Map<Integer, MapChunk> chunkId2Chunk = new HashMap<Integer, MapChunk>();
	protected InputHandler inputHandler = new InputHandler(this);
	protected List<EntityBase> worldEntities = new ArrayList<EntityBase>();
	public EntityDude dude = null;
	protected Random random = new Random();

	public static int MAX_MAP_CHUNK_X = 100;
	
	public MapRogue(long seed) {
		random.setSeed(seed);
		dude = new EntityDude(this, 0, 260);
		worldEntities.add(dude);
		worldGen();
	}

	protected void worldGen() {
		// init with first 20 chunks in either direction
		MapChunk leftChunk = null;
		for(int i=-(MAX_MAP_CHUNK_X/2);i<=(MAX_MAP_CHUNK_X/2);i++) {
			
			// randomly get a biome...
			Biome biome = Biome.getRandomBiome(leftChunk, random);

			// ask the biome to generate a chunk
			MapChunk chunk = biome.generateChunk(i, this, leftChunk, random);

			// store it...
			chunkId2Chunk.put(i, chunk);
			
			// keep a pointer to the chunk on the left...
			leftChunk = chunkId2Chunk.get(i);
			leftChunk.id = i;
		}
	}
	
	public static int getMAX_MAP_CHUNK_X() {
		return MAX_MAP_CHUNK_X;
	}

	public static void setMAX_MAP_CHUNK_X(int mAX_MAP_CHUNK_X) {
		MAX_MAP_CHUNK_X = mAX_MAP_CHUNK_X;
	}

	public List<EntityBase> getWorldEntities() {
		return worldEntities;
	}

	public void addEntity(EntityBase entity) {
		this.worldEntities.add(entity);
	}
	
	public void update(float delta) {

		inputHandler.handleInput();

		for(EntityBase entity : this.worldEntities)
			entity.update(delta);
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
