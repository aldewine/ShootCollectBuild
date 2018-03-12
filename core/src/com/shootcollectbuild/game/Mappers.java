package com.shootcollectbuild.game;

import com.badlogic.ashley.core.ComponentMapper;
import com.shootcollectbuild.game.components.*;

public class Mappers {
    public static final ComponentMapper<PositionComponent> positionComponentMapper = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<ShipComponent> shipComponentMapper = ComponentMapper.getFor(ShipComponent.class);
    public static final ComponentMapper<TargetComponent> targetComponentMapper = ComponentMapper.getFor(TargetComponent.class);
    public static final ComponentMapper<TextureComponent> textureComponentMapper = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<IdComponent> idComponentMapper = ComponentMapper.getFor(IdComponent.class);
    public static final ComponentMapper<MovementComponent> movementComponentMapper = ComponentMapper.getFor(MovementComponent.class);
    public static final ComponentMapper<OwnerIdComponent> ownerIdComponentMapper = ComponentMapper.getFor(OwnerIdComponent.class);
    public static final ComponentMapper<PlayerComponent> playerComponentMapper = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<ShipBlockComponent> shipBlockComponentMapper = ComponentMapper.getFor(ShipBlockComponent.class);
}
