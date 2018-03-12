package com.shootcollectbuild.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.shootcollectbuild.game.Mappers;
import com.shootcollectbuild.game.components.PositionComponent;
import com.shootcollectbuild.game.components.TextureComponent;

import java.util.Comparator;

public class RenderSystem extends IteratingSystem {
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private SpriteBatch batch;

    public RenderSystem(SpriteBatch batch) {
        super(Family.all(TextureComponent.class,
                         PositionComponent.class).get());
        this.batch = batch;

        renderQueue = new Array<Entity>();
        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity entityA, Entity entityB) {
                return (int)Math.signum(Mappers.positionComponentMapper.get(entityB).position.z -
                        Mappers.positionComponentMapper.get(entityA).position.z);
            }
        };
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        renderQueue.sort(comparator);

        batch.begin();

        for (Entity entity : renderQueue) {
            TextureComponent texture = Mappers.textureComponentMapper.get(entity);
            PositionComponent position = Mappers.positionComponentMapper.get(entity);

            if(texture.sprite == null){
                continue;
            }

            texture.sprite.setBounds(position.position.x,
                                     position.position.y,
                                     texture.sprite.getHeight(),
                                     texture.sprite.getWidth());
            texture.sprite.draw(batch);
        }

        batch.end();
        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
