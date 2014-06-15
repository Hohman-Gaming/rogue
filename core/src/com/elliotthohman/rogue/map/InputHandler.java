package com.elliotthohman.rogue.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InputHandler {

	protected MapRogue map = null;
	
	public InputHandler(MapRogue map) {
		this.map = map;
	}
	
	public void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.D)) {
			map.pos.x += .2;
		} 
		if (Gdx.input.isKeyPressed(Keys.A)) {
			map.pos.x -= .2;
		} 
		if (Gdx.input.isKeyPressed(Keys.W)) {
			map.pos.y -= .2;
		} 
		if (Gdx.input.isKeyPressed(Keys.S)) {
			map.pos.y += .2;
		} 
	}
}
