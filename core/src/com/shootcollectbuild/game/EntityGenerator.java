package com.shootcollectbuild.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.shootcollectbuild.game.components.*;

import java.lang.annotation.Target;
import java.util.Random;
import java.util.UUID;

public class EntityGenerator {
    public final Random rand;
    public final Vector2 vector2Temp;
    private PooledEngine engine;

    public EntityGenerator(PooledEngine engine) {
        this.engine = engine;
        this.rand = new Random();
        this.vector2Temp = new Vector2();
    }

    public void create() {
        createBackground();
        createPlayer();
    }

    private void createBackground(){
        Entity entity = engine.createEntity();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        IdComponent id = engine.createComponent(IdComponent.class);

        position.position.x = 0.0f;
        position.position.y = 0.0f;
        position.position.z = 1000.0f;

        texture.sprite = Assets.backgroundRegion;
        id.entityId = String.valueOf(UUID.randomUUID());

        entity.add(position);
        entity.add(texture);
        entity.add(id);

        engine.addEntity(entity);
    }

    private void createPlayer(){
        Entity entity = engine.createEntity();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        TargetComponent target = engine.createComponent(TargetComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        ShipComponent ship = engine.createComponent(ShipComponent.class);
        IdComponent id = engine.createComponent(IdComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);

        position.position.x = 100.0f;
        position.position.y = 100.0f;
        position.position.z = 1.0f;

        texture.sprite = Assets.shipBlockRegion;

        target.targetPos.x = 100.0f;
        target.targetPos.y = 100.0f;

        movement.acceleration = 4.0f;
        movement.maxVelocity = 50.0f;
        movement.velocity.x = 0.0f;
        movement.velocity.y = 0.0f;

        id.entityId = String.valueOf(UUID.randomUUID());

        entity.add(position);
        entity.add(texture);
        entity.add(target);
        entity.add(movement);
        entity.add(ship);
        entity.add(id);
        entity.add(player);

        engine.addEntity(entity);

        addShipBlock(100.0f, 132.0f, 1.0f, 0.0f,
                     position.position.x, position.position.y, id.entityId);
        addShipBlock(132.0f, 100.0f, 1.0f, 0.0f,
                position.position.x, position.position.y, id.entityId);
    }

    private void addShipBlock(float x, float y, float z, float rotation, float ownerX, float ownerY, String id){
        Entity entity = engine.createEntity();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        ShipBlockComponent shipBlock = engine.createComponent(ShipBlockComponent.class);
        OwnerIdComponent ownerId = engine.createComponent(OwnerIdComponent.class);
        IdComponent entityId = engine.createComponent(IdComponent.class);


        position.position.x = x;
        position.position.y = y;
        position.position.z = z;
        position.distanceX = x-ownerX;
        position.distanceY = y-ownerY;

        vector2Temp.x = x - ownerX;
        vector2Temp.y = y - ownerY;

        texture.sprite = Assets.simpleBlockRegion;

        ownerId.ownerId = id;
        entityId.entityId = String.valueOf(UUID.randomUUID());

        entity.add(position);
        entity.add(texture);
        entity.add(shipBlock);
        entity.add(ownerId);
        entity.add(entityId);

        engine.addEntity(entity);
    }
}
