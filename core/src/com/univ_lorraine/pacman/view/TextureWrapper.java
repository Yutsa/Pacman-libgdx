package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.GameElement;

/**
 * @author Édouard WILLISSECK
 */

public abstract class TextureWrapper implements ITexturable {
    /**
     * The object to get the right texture.
     */
    protected GameElement wrappedObject;

    public TextureWrapper() {
        this.wrappedObject = null;
    }

    /**
     * Gets the wrapped object
     *
     * @return The wrapped GameElement.
     */
    public GameElement getWrappedObject() {
        return wrappedObject;
    }

    /**
     * Sets the wrapped object.
     *
     * @param wrappedObject The GameElement to be wrapped.
     */
    public void setWrappedObject(GameElement wrappedObject) {
        if (wrappedObject == null) {
            throw new IllegalArgumentException("The wrapped object can't be null");
        }
        this.wrappedObject = wrappedObject;
    }

    public abstract void update(float deltaTime);
    @Override
    public abstract Texture getTexture();
}
