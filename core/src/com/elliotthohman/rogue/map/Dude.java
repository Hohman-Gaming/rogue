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
	public static final int FACING_LEFT = 0; 
	public static final int FACING_RIGHT = 1; 
	
	public Vector2 pos=new Vector2();
	public Vector2 vel=new Vector2(0,0);  // x=dx, y=dy
	public Vector2 accel=new Vector2(0, Constants.GRAVITY_DY); // gravity always accelerating us down...
	public Rectangle bounds = new Rectangle();
	public int directionFacing = FACING_RIGHT;
	
	
	private Animation walkleft,walkright;
	private MapRogue map;
	private float stateTime = 0;
	


	public Dude (MapRogue map, float x , float y ) { 
		Texture defaultTexture = new Texture(Gdx.files.internal("dude.png"));
	
		TextureRegion[] split = new TextureRegion(defaultTexture).split(20, 20)[0];
		TextureRegion[] mirror = new TextureRegion(defaultTexture).split(20, 20)[0];
		for (TextureRegion region : mirror)
			region.flip(true, false);
		
		walkleft = new Animation(0.25f,mirror[0], mirror[1]);
		
		walkright = new Animation(0.25f,split[0], split[1]);
		
		pos.x = x;
		pos.y = y;
		
		this.map = map;
		
		bounds.x = pos.x;
		bounds.y = pos.y;
		bounds.width = 1;
		bounds.height = 1.5f;
	}
	
	public boolean isStandingOnSolid() {
		
		byte tileCode = map.getTileAtPosition(pos.x, pos.y - 0.25f);

		if (TileCodes.tileInfos[tileCode].solid)
			return true;

		return false;
	}
	
	
	
	
	
	
	public void update (float delta) {

		// use acceleration to update velocity
		vel.x = vel.x + accel.x*delta;
		vel.y = vel.y + accel.y*delta;
		
		// friction should  dampens x velocity, making it smaller and smaller
		if (vel.x > 0) {
			vel.x = vel.x - Constants.FRICTION_FACTOR_DX * delta;
			if (vel.x < 0)
				vel.x = 0;
		} else {
			vel.x = vel.x + Constants.FRICTION_FACTOR_DX * delta;
			if (vel.x > 0)
				vel.x = 0;
		}
		
//		accel.x = accel.x * Constants.friction_factor * delta;
//		accel.y = accel.y * Constants.friction_factor * delta;
		
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
				vel.x = 0;  // when we hit something in the x direction, set our x velocity to 0
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
				vel.y = 0;  // when we hit something like the ground, our velocity has to become 0
			} else {
				break;  // it's an ok update...
			}
		}
		
		pos = newPos;
		
		System.out.println("Accel: " + accel + "   / vel: " + vel);
		
	}

	public void render (SpriteBatch batch, float deltaTime) {
		
		stateTime = stateTime + deltaTime;
		
		if (vel.x > 0)
			directionFacing = FACING_RIGHT;
		else if (vel.x < 0)
			directionFacing = FACING_LEFT;
		
		if (directionFacing == FACING_RIGHT)
			batch.draw(walkright.getKeyFrame(stateTime, true), pos.x, pos.y, 2, 2);
		else
			batch.draw(walkleft.getKeyFrame(stateTime, true), pos.x, pos.y, 2, 2);
		
	}
	
	
	
	
	
	
	
}
