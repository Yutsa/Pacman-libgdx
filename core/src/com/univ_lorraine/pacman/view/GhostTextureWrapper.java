package com.univ_lorraine.pacman.view;

import com.badlogic.gdx.graphics.Texture;
import com.univ_lorraine.pacman.model.GameElement;
import com.univ_lorraine.pacman.model.Ghost;

/**
 * @author Édouard WILLISSECK
 */

public class GhostTextureWrapper extends TextureWrapper {
    public Texture normalTexture;
    public Texture frightenedTexture = new Texture("ghostEscaping.png");
    public Texture deadTexture = new Texture("ghostDead.png");

    /**
     * Creates the wrapper
     *
     * @param wrappedObject The GameElement to be wrapped.
     */
    public GhostTextureWrapper(GameElement wrappedObject, Texture defaultTexture) {
        super(wrappedObject);
        setNormalTexture(defaultTexture);
    }

    public void setNormalTexture(Texture normalTexture) {
        if (normalTexture == null) {
            throw new IllegalArgumentException("NULL texture");
        }
        this.normalTexture = normalTexture;
    }

    @Override
    public void setWrappedObject(GameElement wrappedObject) {
        super.setWrappedObject(wrappedObject);

        if (!(wrappedObject instanceof Ghost)) {
            throw new IllegalArgumentException("GhostTextureWrapper's wrapped object should" +
                    " be a ghost.");
        }
    }

    @Override
    public Texture getTexture() {
        return normalTexture;
    }
}
