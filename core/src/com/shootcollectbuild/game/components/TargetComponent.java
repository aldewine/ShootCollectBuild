package com.shootcollectbuild.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TargetComponent implements Component {
    public final Vector2 targetPos = new Vector2();
    public final String targetId = new String();
}
