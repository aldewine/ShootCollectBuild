package com.shootcollectbuild.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static Texture shipBlock;
    public static Sprite shipBlockRegion;
    public static Texture simpleBlock;
    public static Sprite simpleBlockRegion;
    public static Texture simpleBlockHollow;
    public static Sprite simpleBlockHollowRegion;
    public static Texture background;
    public static Sprite backgroundRegion;

    public static void load(){
        shipBlock = new Texture(Gdx.files.internal("data/shipblock.png"));
        simpleBlock = new Texture(Gdx.files.internal("data/simpleblock.png"));
        background = new Texture(Gdx.files.internal("data/spacebackground.jpg"));
        simpleBlockHollow = new Texture(Gdx.files.internal("data/simpleblockhollow.png"));

        shipBlockRegion = new Sprite(shipBlock);
        simpleBlockRegion = new Sprite(simpleBlock);
        simpleBlockHollowRegion = new Sprite(simpleBlockHollow);
        backgroundRegion = new Sprite(background);
    }
}
