package com.elliotthohman.rogue.screens;

import com.badlogic.gdx.Game;
import com.elliotthohman.rogue.map.MapRogue;
import com.elliotthohman.rogue.render.MapRenderer;

public class GameScreen extends RogueScreen {

	protected MapRogue map; 
	protected MapRenderer renderer;
	
	public GameScreen (Game game) {
		super(game);
	}

	@Override
	public void show () {
		map = new MapRogue();
		renderer = new MapRenderer(map);
//		controlRenderer = new OnscreenControlRenderer(map);
	}

	@Override
	public void render(float delta) {
		map.update();
		renderer.render(delta);
	}

}
