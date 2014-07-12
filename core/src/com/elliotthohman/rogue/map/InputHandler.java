package com.elliotthohman.rogue.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.elliotthohman.rogue.Constants;

public class InputHandler {

	protected MapRogue map = null;
	
	public InputHandler(MapRogue map) {
		this.map = map;
	}
	
	public void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.D)) {
			map.dude.vel.x += 5.0f;
		} 
		if (Gdx.input.isKeyPressed(Keys.A)) {
			map.dude.vel.x += -5.0f;
		} 
//		if (Gdx.input.isKeyPressed(Keys.W)) {
//			map.dude.vel.y -= .2;
//		} 
//		if (Gdx.input.isKeyPressed(Keys.S)) {
//			map.dude.vel.y += .2;
//		} 
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			
			if (map.dude.isStandingOnSolid()){
				
				map.dude.vel.y = Constants.JUMP_Y_ACCEL;	
				
			}
			
			
		} 
	}
}
