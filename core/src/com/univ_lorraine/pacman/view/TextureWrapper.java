package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.GameElement;

/**
 * @author Édouard WILLISSECK
 */

public abstract class TextureWrapper {
    private GameElement wrappedObject;

    public TextureWrapper(GameElement wrappedObject) {
        this.wrappedObject = wrappedObject;
    }

    public GameElement getWrappedObject() {
        return wrappedObject;
    }

    public void setWrappedObject(GameElement wrappedObject) {
        if (wrappedObject == null)
        {
            throw new IllegalArgumentException("The wrapped object can't be null");
        }
        this.wrappedObject = wrappedObject;
    }

    abstract public Texture getTexture();
}
