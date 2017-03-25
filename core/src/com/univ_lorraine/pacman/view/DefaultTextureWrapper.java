package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;

/**
 * @author Édouard WILLISSECK
 */

// TODO: 25/03/17 Shoudln't have a wrapped object for this wrapper, it's useless.

public class DefaultTextureWrapper implements ITexturable {
    /**
     * The default texture for the wrapped GameElement.
     */
    private Texture mDefaultTexture;

    /**
     * Creates the wrapper.
     *
     * @param defaultTexture The default texture for the wrapped object.*/
    public DefaultTextureWrapper(Texture defaultTexture) {
        setDefaultTexture(defaultTexture);
    }

    /**
     * Sets the default Texture.
     *
     * @param defaultTexture The default Texture.
     */
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
