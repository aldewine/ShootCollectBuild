package com.shootcollectbuild.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.shootcollectbuild.game.GameState;
import com.shootcollectbuild.game.Mappers;
import com.shootcollectbuild.game.components.MovementComponent;
import com.shootcollectbuild.game.components.PositionComponent;
import com.shootcollectbuild.game.components.TargetComponent;

public class MovementSystem extends IteratingSystem{

    float mouseAngle = 0.0f;

    public MovementSystem() {
        super(Family.all(MovementComponent.class,
                         TargetComponent.class,
                         PositionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(GameState.isGamePaused()){
            System.out.print("Position System - game is paused!");
            return;
        }

        MovementComponent movement = Mappers.movementComponentMapper.get(entity);
        TargetComponent target = Mappers.targetComponentMapper.get(entity);
        PositionComponent position = Mappers.positionComponentMapper.get(entity);

        if(target.targetPos.x != position.position.x ||
           target.targetPos.y != position.position.y) {
            Vector2 mouseR = new Vector2(target.targetPos.x - position.position.x,
                    target.targetPos.y - position.position.y);
            mouseAngle = (int) mouseR.angle();

            movement.velocity.x = (int) (movement.maxVelocity * (float) Math.cos(Math.toRadians(mouseAngle)));
            movement.velocity.y = (int) (movement.maxVelocity * (float) Math.sin(Math.toRadians(mouseAngle)));
        }else{
            movement.velocity.x = 0.0f;
            movement.velocity.y = 0.0f;
        }
        System.out.print("mouseAngle:" + mouseAngle);
        System.out.print(" speedX:" + movement.velocity.x);
        System.out.print(" speedY:" + movement.velocity.y);
        System.out.print(" targetX:" + target.targetPos.x);
        System.out.print(" targetX:" + target.targetPos.y);
        System.out.print(" positionX:" + position.position.x);
        System.out.print(" positionY:" + position.position.y);
        System.out.println();
    }
}
