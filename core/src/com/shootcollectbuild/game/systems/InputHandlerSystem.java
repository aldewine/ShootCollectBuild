package com.shootcollectbuild.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.shootcollectbuild.game.GameState;
import com.shootcollectbuild.game.InputHandlerIF;
import com.shootcollectbuild.game.Mappers;
import com.shootcollectbuild.game.ShootCollectBuild;
import com.shootcollectbuild.game.components.PlayerComponent;
import com.shootcollectbuild.game.components.PositionComponent;
import com.shootcollectbuild.game.components.TargetComponent;
import com.shootcollectbuild.game.components.TextureComponent;

public class InputHandlerSystem extends IteratingSystem implements InputHandlerIF {

    private static Vector2 target = new Vector2();
    ShootCollectBuild game;

    public InputHandlerSystem(ShootCollectBuild game) {
        super(Family.all(PositionComponent.class,
                         TextureComponent.class,
                         TargetComponent.class,
                         PlayerComponent.class).get());
        this.game = game;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(GameState.isGamePaused()){
            System.out.print("Position System - game is paused!");
            return;
        }

        TargetComponent target = Mappers.targetComponentMapper.get(entity);

        target.targetPos.x = this.target.x;
        target.targetPos.y = this.target.y;

    }

    @Override
    public void target(float x, float y) {

        TextureComponent texture = null;
        PositionComponent position = null;
        TargetComponent target = null;
        for(int i = 0; i < getEntities().size(); i++){
            texture = Mappers.textureComponentMapper.get(getEntities().get(i));
            position = Mappers.positionComponentMapper.get(getEntities().get(i));
            target = Mappers.targetComponentMapper.get(getEntities().get(i));
            if(texture.sprite.getBoundingRectangle().contains(x, this.game.GAME_HEIGHT - y)){
                target.targetPos.x = position.position.x;
                target.targetPos.y = position.position.y;
                GameState.pauseGame();
                return;
            }
        }

        GameState.startGame();
        this.target.x = x;
        this.target.y = this.game.GAME_HEIGHT - y;
        System.out.println("****************");
        System.out.println("target.x:" + this.target.x);
        System.out.println("target.y:" + this.target.y);
    }
}
