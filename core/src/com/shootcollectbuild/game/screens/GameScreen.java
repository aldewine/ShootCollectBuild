package com.shootcollectbuild.game.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.shootcollectbuild.game.EntityGenerator;
import com.shootcollectbuild.game.RawInputHandler;
import com.shootcollectbuild.game.ShootCollectBuild;
import com.shootcollectbuild.game.systems.InputHandlerSystem;
import com.shootcollectbuild.game.systems.MovementSystem;
import com.shootcollectbuild.game.systems.PositionSystem;
import com.shootcollectbuild.game.systems.RenderSystem;

public class GameScreen extends ScreenAdapter{

    ShootCollectBuild game;
    EntityGenerator entityGenerator;
    PooledEngine engine;

    public GameScreen(ShootCollectBuild game) {
        this.game = game;
        engine = new PooledEngine();
        entityGenerator = new EntityGenerator(engine);
        entityGenerator.create();

        InputHandlerSystem inputHandlerSystem = new InputHandlerSystem(this.game);
        RawInputHandler rawInputHandler = new RawInputHandler(inputHandlerSystem);
        Gdx.input.setInputProcessor(rawInputHandler);
        engine.addSystem(inputHandlerSystem);
        engine.addSystem(new MovementSystem());
        engine.addSystem(new PositionSystem());
        engine.addSystem(new RenderSystem(game.batch));

    }

    public void update (float deltaTime) {
        if (deltaTime > 0.1f) deltaTime = 0.1f;

        engine.update(deltaTime);
    }

    @Override
    public void render(float delta) {
        update(delta);
    }
}
