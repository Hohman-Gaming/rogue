package com.elliotthohman.rogue.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.elliotthohman.rogue.map.MapChunk;
import com.elliotthohman.rogue.map.MapRogue;
import com.elliotthohman.rogue.map.TileCodes;
import com.elliotthohman.rogue.map.entity.EntityBase;

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
					byte tileCode = chunk.tiles[x][y];
					batch.draw(
							TileCodes.tileTextures[tileCode],
							chunk.getWorldCoordinateLeftSideOfChunk()+x,
							y, TileCodes.tileInfos[tileCode].tile_dx, TileCodes.tileInfos[tileCode].tile_dy);
				}
			}
			
		}
		
		// render objects, layer by layer
		for(byte priority=EntityBase.RENDER_PRIORITY_BACKGROUND; priority >= EntityBase.RENDER_PRIORITY_FOREGROUND; priority--)
			for(EntityBase entity : map.getWorldEntities())
				if (entity.getRenderPriority() == priority)
					entity.render(batch, deltaTime);
		
		batch.end();

//		fps.log();
	}
	
}
