package com.sbt.codeit.core;

import com.badlogic.gdx.Game;
import com.sbt.codeit.core.view.Drawer;


public class TanksGame extends Game {

	private Drawer drawer;

	@Override
	public void create () {
		drawer = new Drawer();
	}

	@Override
	public void render () {
		drawer.draw();
	}

}
