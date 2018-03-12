package com.shootcollectbuild.game;

import com.badlogic.gdx.InputAdapter;

public class RawInputHandler extends InputAdapter {
    private InputHandlerIF listener;

    public RawInputHandler(InputHandlerIF listener) {
        this.listener = listener;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        listener.target(screenX,screenY);
        return true;
    }
}
