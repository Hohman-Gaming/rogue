package com.elliotthohman.rogue;

import com.badlogic.gdx.Game;
import com.elliotthohman.rogue.screens.TitleScreen;

public class RogueGame extends Game {
	
	@Override
	public void create () {
		setScreen(new TitleScreen(this));
	}

}
