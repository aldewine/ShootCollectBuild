package com.shootcollectbuild.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shootcollectbuild.game.screens.GameScreen;

public class ShootCollectBuild extends Game {
	public SpriteBatch batch;
	public float GAME_WIDTH = 0.0f;
	public float GAME_HEIGHT = 0.0f;

	@Override
	public void create() {
		GameState.startGame();
		batch = new SpriteBatch();
		this.GAME_HEIGHT = Gdx.graphics.getHeight();
		this.GAME_WIDTH = Gdx.graphics.getWidth();
		Assets.load();
		setScreen(new GameScreen(this));
	}
}
