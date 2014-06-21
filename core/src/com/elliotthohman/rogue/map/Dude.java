package com.elliotthohman.rogue.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Dude {
	
	public static final float DUDES_MAX_VELOCITY_Y = 30.0f;
	public static final float DUDES_MIN_VELOCITY_Y = -30.0f;
	public static final float DUDES_MAX_VELOCITY_X = 15.0f;
	public static final float DUDES_MIN_VELOCITY_X = -15.0f;
	public static final float MIN_DELTA = 0.001f;
	
	public Vector2 pos=new Vector2();
	public Vector2 vel=new Vector2(0,0);  // x=dx, y=dy
	public Vector2 accel=new Vector2(0, Constants.gravity_dy); // gravity always accelerating us down...
	public Rectangle bounds = new Rectangle();
	
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
		
		bounds.x = pos.x;
		bounds.y = pos.y;
		bounds.width = 1;
		bounds.height = 1.5f;
	}
	
	public void update (MapRogue map, float delta) {

		// use acceleration to update velocity
		vel.x = vel.x + accel.x*delta;
		vel.y = vel.y + accel.y*delta;
		
		// friction should  dampens x velocity, making it smaller and smaller
		vel.x = vel.x * Constants.friction_factor;
		
		// let's implement terminal velocity
		if (vel.x >= DUDES_MAX_VELOCITY_X)
			vel.x = DUDES_MAX_VELOCITY_X;
		if (vel.x <= DUDES_MIN_VELOCITY_X)
			vel.x = DUDES_MIN_VELOCITY_X;
		
		if (vel.y >= DUDES_MAX_VELOCITY_Y)
			vel.y = DUDES_MAX_VELOCITY_Y;
		if (vel.y <= DUDES_MIN_VELOCITY_Y)
			vel.y = DUDES_MIN_VELOCITY_Y;
		
		// use velocity to update position
		Vector2 newPos = new Vector2(pos);
		
		float desiredDelta = vel.x*delta;
		byte tileCode;
		while(Math.abs(desiredDelta) > MIN_DELTA) {
			newPos.x = pos.x + desiredDelta;
			// see if newPos intersects with SOLID tile, and if so, refuse to update it
			tileCode = map.getTileAtPosition(newPos.x+bounds.width, newPos.y);
			if (TileCodes.tileInfos[tileCode].solid) {
				// not allowed, we hit something
				desiredDelta = desiredDelta/2;
				newPos.x = pos.x;
			} else {
				break; // it's an ok update
			}
		}
		
		desiredDelta = vel.y*delta;
		while(Math.abs(desiredDelta) > MIN_DELTA) {
			newPos.y = pos.y + desiredDelta;
			// see if newPos intersects with SOLID tile, and if so, refuse to update it
			tileCode = map.getTileAtPosition(newPos.x, newPos.y);
			if (TileCodes.tileInfos[tileCode].solid) {
				// not allowed, we hit something
				desiredDelta = desiredDelta/2;
				newPos.y = pos.y;
			} else {
				break;  // it's an ok update...
			}
		}
		
		pos = newPos;
		
	}

	public void render (SpriteBatch batch, float deltaTime) {
		
		stateTime = stateTime + deltaTime;
		
		
		batch.draw(walkright.getKeyFrame(stateTime, true), pos.x, pos.y, 2, 2);
		
		
		
		
	}
	
	
	
	
	
	
	
}
