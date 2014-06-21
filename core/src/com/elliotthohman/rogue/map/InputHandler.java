package com.elliotthohman.rogue.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InputHandler {

	protected MapRogue map = null;
	
	public InputHandler(MapRogue map) {
		this.map = map;
	}
	
	public void handleInput() {
		map.dude.accel.x = 0;  // no acceleration except from keys
		if (Gdx.input.isKeyPressed(Keys.D)) {
			map.dude.accel.x = 5.0f;
		} 
		if (Gdx.input.isKeyPressed(Keys.A)) {
			map.dude.accel.x = -5.0f;
		} 
//		if (Gdx.input.isKeyPressed(Keys.W)) {
//			map.dude.vel.y -= .2;
//		} 
//		if (Gdx.input.isKeyPressed(Keys.S)) {
//			map.dude.vel.y += .2;
//		} 
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			map.dude.vel.y += 8.0f;
		} 
	}
}
