package com.elliotthohman.rogue.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileCodes {

	public static class TileInfo {
		public boolean solid = false;
		public float tile_dx = 1;
		public float tile_dy = 1;
		public TileInfo(boolean solid) {
			this.solid = solid;
		}
		public TileInfo(boolean solid, float dx, float dy) {
			this.solid = solid;
			this.tile_dx = dx;
			this.tile_dy = dy;
		}
	}
	
	public static byte TILE_AIR=0;
	public static byte TILE_GRASS=1;
	public static byte TILE_DIRT=2;
	public static byte TILE_WATER=3;
	
	public static TextureRegion[] tileTextures = new TextureRegion[] {
		new TextureRegion(new Texture(Gdx.files.internal("air.png")), 0, 0, 32, 32),
		new TextureRegion(new Texture(Gdx.files.internal("grass.png")), 0, 0, 32, 32),
		new TextureRegion(new Texture(Gdx.files.internal("dirt.png")), 0, 0, 32, 32),
		new TextureRegion(new Texture(Gdx.files.internal("water.png")), 0, 0, 32, 32),
	};
	
	public static TileInfo[] tileInfos = new TileInfo[] {
		new TileInfo(false),			//air - not solid
		new TileInfo(true),				// grass - solid
		new TileInfo(true),				// dirt - solid
		new TileInfo(false),			// water
	};
}
