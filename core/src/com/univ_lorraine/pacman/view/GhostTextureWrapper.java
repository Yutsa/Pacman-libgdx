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
    public GhostTextureWrapper(GameElement wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public void setWrappedObject(GameElement wrappedObject) {
        super.setWrappedObject(wrappedObject);

        if (!(wrappedObject instanceof Ghost)) {
            throw new IllegalArgumentException("GhostTextureWrapper's wrapped object should" +
                    " be a ghost.");
        }

        switch (((Ghost) wrappedObject).getColor()) {
            case RED:
                normalTexture = new Texture("ghost1.png");
                break;
            case PINK:
                normalTexture = new Texture("ghost2.png");
                break;
            case BLUE:
                normalTexture = new Texture("ghost3.png");
                break;
            case YELLOW:
                normalTexture = new Texture("ghost4.png");
                break;
        }
    }

    @Override
    public Texture getTexture() {
        return normalTexture;
    }
}
