package com.elliotthohman.rogue.map.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.elliotthohman.rogue.map.MapRogue;

public class EntityTree extends EntityBase {

	static protected TextureRegion tree_top_texture = new TextureRegion(new Texture(Gdx.files.internal("treetop.png")), 0, 0, 128, 128);
	static protected TextureRegion tree_trunk_texture = new TextureRegion(new Texture(Gdx.files.internal("treetrunk.png")), 0, 0, 32, 32);
	static protected TextureRegion tree_base_texture = new TextureRegion(new Texture(Gdx.files.internal("treetrunk_base.png")), 0, 0, 32, 32);
	protected int height;
	
	
	public EntityTree(MapRogue map, float x, float y, int height) {
		super(map, x, y, EntityBase.RENDER_PRIORITY_BACKGROUND);
		this.height = height;
	}

	@Override
	public void render (SpriteBatch batch, float deltaTime) {

		super.render(batch, deltaTime);
		
		batch.draw(tree_base_texture, pos.x, pos.y, 1, 1);
		float y = pos.y+1;
		for(int h=0;h<height;h++)
			batch.draw(tree_trunk_texture, pos.x, y++, 1, 1);
		batch.draw(tree_top_texture, pos.x-1.8f, y-.5f, 4, 4);
	}

	
}
