package com.elliotthohman.rogue.map.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.elliotthohman.rogue.map.MapRogue;

public class EntityBase {

	public static byte RENDER_PRIORITY_FOREGROUND = 0;
	public static byte RENDER_PRIORITY_BACKGROUND = 10;
	
	protected MapRogue map;
	protected float stateTime = 0;
	public Vector2 pos=new Vector2();
	protected byte renderPriority; // 0 means always on to


	public EntityBase(MapRogue map, float x, float y, byte renderPriority) {
		this.map = map;
		pos.x = x;
		pos.y = y;
		this.renderPriority = renderPriority;
	}
	
	// default is no update
	public void update (float delta) {
	}

	public byte getRenderPriority() {
		return renderPriority;
	}

	public void render (SpriteBatch batch, float deltaTime) {
		stateTime = stateTime + deltaTime;
	}
	
}
