package com.elliotthohman.rogue.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Dude {
	
	public Vector2 pos=new Vector2();
	public Vector2 vel=new Vector2();
	public Vector2 accel=new Vector2();

	private Animation walkleft,walkright;
	float stateTime = 0;


	public Dude (float x , float y ) { 
		Texture defaultTexture = new Texture(Gdx.files.internal("dude.png"));
	
		TextureRegion[] split = new TextureRegion(defaultTexture).split(20, 20)[0];
		TextureRegion[] mirror = new TextureRegion(defaultTexture).split(20, 20)[0];
		for (TextureRegion region : mirror)
			region.flip(true, false);
		
		walkleft = new Animation(0.25f,mirror[0], mirror[1]);
		
		walkright = new Animation(0.25f,split[0], split[1]);
		
		pos.x = x;
		pos.y = y;
	}
	
	public void update () {
		
		
		
	}

	public void render (SpriteBatch batch, float deltaTime) {
		
		stateTime = stateTime + deltaTime;
		
		
		batch.draw(walkright.getKeyFrame(stateTime, true), pos.x, pos.y, 2, 2);
		
		
		
		
	}
	
	
	
	
	
	
	
}
