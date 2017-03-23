package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.GameElement;

/**
 * @author Édouard WILLISSECK
 *         The abstract class that wrapps an object to get the right texture.
 */

public abstract class TextureWrapper {
    /**
     * The object to get the right texture.
     */
    private GameElement wrappedObject;

    /**
     * Creates the wrapper
     *
     * @param wrappedObject The GameElement to be wrapped.
     */
    public TextureWrapper(GameElement wrappedObject) {
        this.wrappedObject = wrappedObject;
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

    /**
     * Returns the right texture for the wrapped GameElement.
     *
     * @return The right texture for the wrapped GameElement.
     */
    abstract public Texture getTexture();
}
