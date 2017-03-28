package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;

/**
 * @author Édouard WILLISSECK
 */

public class SuperPelletTextureWrapper extends TextureWrapper {
    private float time;
    private int state;

    private Texture[] mTextures;

    public SuperPelletTextureWrapper() {
        mTextures = new Texture[2];
        mTextures[0] = new Texture("superpellet.png");
        mTextures[1] = new Texture("superpellet-2.png");
    }

    @Override
    public void update(float deltaTime) {
        time += deltaTime;
        state = (int) (time * 5) % 2;
    }

    @Override
    public Texture getTexture() {
        return mTextures[state];
    }
}
