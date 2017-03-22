package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.GameElement;

/**
 * @author Édouard WILLISSECK
 */

class DefaultTextureWrapper extends TextureWrapper {
    private Texture mDefaultTexture;

    public DefaultTextureWrapper(GameElement wrappedObject, Texture defaultTexture) {
        super(wrappedObject);
        setDefaultTexture(defaultTexture);
    }

    public Texture getDefaultTexture() {
        return mDefaultTexture;
    }

    public void setDefaultTexture(Texture defaultTexture) {
        if (defaultTexture == null) {
            throw new IllegalArgumentException("defaultTexture can't be null");
        }
        this.mDefaultTexture = defaultTexture;
    }

    @Override
    public Texture getTexture() {
        return mDefaultTexture;
    }
}
