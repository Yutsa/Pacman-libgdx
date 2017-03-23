package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.GameElement;

/**
 * @author Édouard WILLISSECK
 */

public class DefaultTextureWrapper extends TextureWrapper {
    /**
     * The default texture for the wrapped GameElement.
     */
    private Texture mDefaultTexture;

    /**
     * Creates the wrapper.
     *
     * @param wrappedObject  The object to be wrapped.
     * @param defaultTexture The default texture for the wrapped object.
     */
    public DefaultTextureWrapper(GameElement wrappedObject, Texture defaultTexture) {
        super(wrappedObject);
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
