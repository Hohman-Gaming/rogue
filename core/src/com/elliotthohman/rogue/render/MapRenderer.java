package com.elliotthohman.rogue.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.elliotthohman.rogue.map.MapChunk;
import com.elliotthohman.rogue.map.MapRogue;
import com.elliotthohman.rogue.map.TileCodes;

public class MapRenderer {

	protected MapRogue map;
	protected OrthographicCamera cam;
	protected SpriteCache cache;
	protected SpriteBatch batch = new SpriteBatch(5460);
	private FPSLogger fps = new FPSLogger();
	private BitmapFont font = new BitmapFont();
	public static float SCREEN_TILE_WIDTH = 64f;
	public static float SCREEN_TILE_HEIGHT = 48f;
	
	public MapRenderer(MapRogue map) {
		this.map = map;
		this.cam = new OrthographicCamera(SCREEN_TILE_WIDTH, SCREEN_TILE_HEIGHT);
		this.cache = new SpriteCache(this.map.getMapWidth()* this.map.getMapHeight(), false);
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.setScale(.25f);
		createAnimations();
		createBlocks();
	}
	
	public void createBlocks() {
		
	}
	
	public void createAnimations() {
		
		
		
		
	}
	
	public void render(float deltaTime) {
		Gdx.gl.glDisable(GL20.GL_BLEND);
		
		this.cam.position.set(map.dude.pos.x, map.dude.pos.y, 0);
		this.cam.update();
		
		batch.setProjectionMatrix(cam.combined);
		batch.begin();

		float leftX = map.dude.pos.x - MapRenderer.SCREEN_TILE_WIDTH/2;
		float rightX = map.dude.pos.x + MapRenderer.SCREEN_TILE_WIDTH/2;
		float bottomY = map.dude.pos.y - MapRenderer.SCREEN_TILE_HEIGHT/2;
		float topY = map.dude.pos.y + MapRenderer.SCREEN_TILE_HEIGHT/2;
		
		int leftChunk = (int)Math.floor((double)leftX/MapChunk.CHUNK_DX);
		int rightChunk = (int)Math.ceil((double)rightX/MapChunk.CHUNK_DX);
		
		for(int chunkId = leftChunk;chunkId<=rightChunk;chunkId++) {
			MapChunk chunk = map.getChunk(chunkId);
			
			for(int x=0;x<MapChunk.CHUNK_DX;x++) {
				for(int y=(int)bottomY;y<=topY;y++) {
					batch.draw(
							TileCodes.tileTextures[chunk.tiles[x][y]],
							chunkId*MapChunk.CHUNK_DX+x,
							y, 1, 1);
				}
			}
			
		}
		
//		font.draw(batch, "Left Chunk: " + map.getChunkAtPosition(leftX).id, leftX+1, SCREEN_TILE_HEIGHT-1);
//		font.draw(batch, "Left Chunk: " + map.getChunkAtPosition(leftX).id, 0,32);
		
		map.dude.render(batch ,deltaTime );
		
		batch.end();

//		fps.log();
	}
	
}
