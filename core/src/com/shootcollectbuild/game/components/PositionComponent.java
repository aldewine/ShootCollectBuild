package com.shootcollectbuild.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class PositionComponent implements Component {
    public final Vector3 position = new Vector3();
    public final Vector2 scale = new Vector2(1.0f,1.0f);
    public float distanceX = 0.0f;
    public float distanceY = 0.0f;
}
