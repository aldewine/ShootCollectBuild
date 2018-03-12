package com.shootcollectbuild.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.shootcollectbuild.game.GameState;
import com.shootcollectbuild.game.Mappers;
import com.shootcollectbuild.game.components.MovementComponent;
import com.shootcollectbuild.game.components.OwnerIdComponent;
import com.shootcollectbuild.game.components.PositionComponent;
import com.shootcollectbuild.game.components.TargetComponent;

public class PositionSystem extends IteratingSystem {

    public PositionSystem() {
        super(Family.all(PositionComponent.class).get());
    }
    private Vector2 deltaVelocity = new Vector2(0.0f,0.0f);


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(GameState.isGamePaused()){
            System.out.print("Position System - game is paused!");
            return;
        }
        PositionComponent position = Mappers.positionComponentMapper.get(entity);
        if(null != Mappers.shipComponentMapper.get(entity)) {
            MovementComponent movement = Mappers.movementComponentMapper.get(entity);
            TargetComponent target = Mappers.targetComponentMapper.get(entity);
            deltaVelocity.x = movement.velocity.x * deltaTime;
            deltaVelocity.y = movement.velocity.y * deltaTime;

            if(Math.abs(target.targetPos.x - position.position.x) < Math.abs(deltaVelocity.x)){
                position.position.x = target.targetPos.x;
            }else{
                position.position.x += deltaVelocity.x;
            }
            if(Math.abs(target.targetPos.y - position.position.y) < Math.abs(deltaVelocity.y)){
                position.position.y = target.targetPos.y;
            }else{
                position.position.y += deltaVelocity.y;
            }

        }else if(null != Mappers.shipBlockComponentMapper.get(entity)){
            OwnerIdComponent ownerId = Mappers.ownerIdComponentMapper.get(entity);
            PositionComponent ownerPosition = null;
            for(int i = 0; i < getEntities().size(); i++){

                if(ownerId.ownerId == Mappers.idComponentMapper.get(getEntities().get(i)).entityId){
                    ownerPosition = Mappers.positionComponentMapper.get(getEntities().get(i));
                    break;
                }

            }
            if(null != ownerPosition) {
                position.position.x = ownerPosition.position.x + position.distanceX;
                position.position.y = ownerPosition.position.y + position.distanceY;
            }
        }
    }
}
